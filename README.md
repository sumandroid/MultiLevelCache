# MultiLevelCache
A java based in-memory multi-level cache application

Each layer will store Key-Value pairs of data. Both KEY and VALUE are of type String. L1 is the top most layer with most priority. LN is the last layer with least priority. LRU is selected as
cache eviction policy.

 READ KEY Operation
Read will start from L1 level. If KEY is found at this layer then this value will be returned. If KEY is not found at this layer then try to read from next layer. Keep doing this until the value of KEY is found at any level or the last level has been reached. If the value of KEY is found at any level, then this VALUE should also be written into all previous levels of cache which have higher priority than this level. Total Read time is the sum of Read time of all levels where READ operation was attempted and Write time of all levels where WRITE operation was attempted.
It will print the VALUE of KEY and total time taken to read it.

WRITE KEY Operation
Write will start from L1 level. Write the value of KEY at this level and all levels below it. If at any level value of KEY is same as given VALUE then don’t write again and return. Total Write time is the sum of WRITE time of all levels where WRITE operation was attempted and Read time of levels where READ operation was attempted. 
It will print total time taken to write this KEY-VALUE information.
