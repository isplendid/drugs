create table `DISTRIBUTOR` (
  `NAME` TEXT NOT NULL,
  `ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

create table `DRUG` (
  `NAME` TEXT NOT NULL,
  `DISTRIBUTOR_ID` BIGINT NOT NULL,
  `ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

alter table `DRUG` add constraint
  `DISTRIBUTOR_PK` foreign key(`DISTRIBUTOR_ID`) references `DISTRIBUTOR`(`ID`)
  on update NO ACTION on delete NO ACTION;

create table `INGREDIENT` (
  `NAME` TEXT NOT NULL,
  `AMOUNT` INTEGER NOT NULL,
  `DRUG_ID` BIGINT NOT NULL,
  `ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

alter table `INGREDIENT` add constraint
  `DRUG_PK` foreign key(`DRUG_ID`) references `DRUG`(`ID`)
  on update NO ACTION on delete NO ACTION;

insert into `DISTRIBUTOR` values ('JutaVit', 1);
insert into `DISTRIBUTOR` values ('Dr. Aliment', 2);

insert into `DRUG` values ('Lecitin PRO', 1, 1);
insert into `DRUG` values ('Lizin-C', 2, 2);
insert into `DRUG` values ('Magnézium + B6', 1, 3);

insert into `INGREDIENT` values ('Lecitin', 1200, 1, 1);
insert into `INGREDIENT` values ('L-izin', 714, 2, 2);
insert into `INGREDIENT` values ('C vitamin', 30, 2, 3);
insert into `INGREDIENT` values ('Magnézium', 250, 3, 4);
insert into `INGREDIENT` values ('B6 vitamin', 2, 3, 5);
insert into `INGREDIENT` values ('D3 vitamin', 10, 3, 6);