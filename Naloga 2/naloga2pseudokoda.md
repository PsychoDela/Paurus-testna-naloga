```
Class DataEntry:
    matchId: string
    marketId: string
    outcomeId: string
    specifiers: string
    dateInsert: timestamp
    sequence: long

matchQueues: Map<string, Queue<DataEntry>>
sequenceCounter: long = 0
minDateInsert: timestamp = MAX_VALUE
maxDateInsert: timestamp = MIN_VALUE
BATCH_SIZE: constant = 1000

Function initializeDatabase():
    CREATE TABLE match_data (
        match_id, market_id, outcome_id, specifiers,
        date_insert, sequence
        PRIMARY KEY (match_id, sequence)
    )
    CREATE INDEX on match_id

Function processFile(filePath):
    Initialize threadPool
    Open file reader
    
    For each line in file (skip header):
        Split line by "|" into [matchId, marketId, outcomeId, specifiers]
        currentTime = now()
        entry = new DataEntry(
            matchId, marketId, outcomeId, specifiers,
            currentTime, increment(sequenceCounter)
        )
        
        Add entry to matchQueues[matchId]
        Update minDateInsert and maxDateInsert with currentTime
        
        If matchQueues[matchId].size >= BATCH_SIZE:
            batch = remove matchQueues[matchId]
            threadPool.submit(processBatch(batch))
    
    For each queue in matchQueues:
        If queue not empty:
            threadPool.submit(processBatch(queue))
    
    Wait for threadPool to complete

Function processBatch(queue):
    Open database connection
    Prepare insert statement
    
    For each entry in queue:
        Add to batch: (entry.matchId, entry.marketId, entry.outcomeId,
                      entry.specifiers, entry.dateInsert, entry.sequence)
    
    Execute batch insert
    Close connection

Function updateMinMaxDate(date):
    If date < minDateInsert: minDateInsert = date
    If date > maxDateInsert: maxDateInsert = date

Function main():
    Initialize database
    Process file
    Return minDateInsert, maxDateInsert```