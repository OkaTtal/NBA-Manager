ALTER TABLE table_name ADD COLUMN new_id INT; /*This adds a new column called new_id of type INT to the table table_name.*/
SET @seq = 0; /*This initializes a session variable @seq with a value of 0.*/
UPDATE table_name SET new_id = (@seq := @seq + 1) ORDER BY id; /*This updates the new_id column with sequential values starting from 1.*/
ALTER TABLE table_name DROP COLUMN id; /*This drops the id column from the table.*/
ALTER TABLE table_name MODIFY COLUMN new_id INT FIRST; /*This modifies the new_id column to be the first column in the table.*/
ALTER TABLE table_name CHANGE new_id id INT; /*This renames the new_id column to id and changes its data type to INT.*/
UPDATE table_name 
SET salary = CASE 
    WHEN GP = 0 THEN salary 
    WHEN PTS / GP > 20 THEN 3000 
    WHEN PTS / GP <= 20 AND salary = 3000 THEN 1000 
    ELSE salary 
END; 
