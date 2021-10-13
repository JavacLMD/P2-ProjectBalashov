CREATE KEYSPACE IF NOT EXIST scifi WITH REPLICATION={'class':'SimpleStrategy','replication_factor'=1};
  USE scifi;
CREATE TABLE IF NOT EXIST orders(
  order_id int,
  customer_id int
  item_id int,
  price double,
  primary key (order_id, customer_id)
 );