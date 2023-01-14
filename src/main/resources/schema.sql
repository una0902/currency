CREATE TABLE IF NOT EXISTS parameter (
	chartname VARCHAR(100) NOT NULL,
	chtname VARCHAR(10) NOT NULL,
	PRIMARY KEY (chartname)
);
CREATE TABLE IF NOT EXISTS summary (
   id bigint NOT NULL,
   time VARCHAR(50) NOT NULL,
   disclaimer VARCHAR(600),
   chartname VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS detail (
   id bigint NOT NULL,
   chartname VARCHAR(100) NOT NULL,
   code VARCHAR(10) NOT NULL,
   symbol VARCHAR(60) NOT NULL,
   rate VARCHAR(17) NOT NULL,
   description VARCHAR(600) NOT NULL,
   ratefloat VARCHAR(17) NOT NULL,
   PRIMARY KEY (id,chartname,code)
);