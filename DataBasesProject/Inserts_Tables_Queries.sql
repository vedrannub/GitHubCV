select * from tabela;
select * from ima;
select * from tim;
select * from natprevar;


select * from lifce;
select * from tip;

insert into ima(liga,ime) values('Premier League','Chelsea');
insert into ima(liga,ime) values('TH','Barcelona');
insert into ima(liga,ime) values('CA','Manchester United');
insert into ima(liga,ime) values('SE','Dortmund');
insert into ima(liga,ime) values('CN','Krasnodar');
insert into ima(liga,ime) values('BR','Stevenage');
insert into ima(liga,ime) values('US','Portsmouth');
insert into ima(liga,ime) values('PH','Boston UTD');
insert into ima(liga,ime) values('PT','Kiddermeister');
insert into ima(liga,ime) values('KZ','Hansa Rostock');
insert into ima(liga,ime) values('SY','Porto');
insert into ima(liga,ime) values('MX','Benfica');


select * from ima;

select * from natprevar;

ALTER TABLE natprevar ALTER COLUMN rezultat TYPE VARCHAR(50);


ALTER TABLE natprevar ALTER COLUMN rezultat TYPE CHARACTER VARYING(10);


insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (1,'Barcelona','Porto',22,'PT','Barcelona','Porto');
insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (2,'Benfica','Porto',10,'PT','Benfica','Porto');
insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (3,'Kiddermeister','Porto',00,'US','Kiddermeister','Porto');
insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (4,'Dortmund','Porto',01,'MX','Dortmund','Porto');
insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (5,'Benfica','Hansa Rostock',11,'PT','Benfica','Hansa Rostock');
insert into natprevar(id_natprevar,tim1,tim2,rezultat,liga,ime,gostinime) values (6,'Krasnodar','Stevenage',33,'US','Krasnodar','Stevenage');







ALTER TABLE natprevar
ADD COLUMN rezultat varchar(50);

alter table lifce
add column utakmica varchar(50);

alter table tip
drop column sistem;

alter table tip
drop column fiks;

alter table tip
add column sistem varchar(5);

alter table tip
add column uplata integer;

alter table lifce
add column dobitno boolean;

select * from lifce;
select * from tip;

select * from natprevar;

insert into lifce(id_lifce,dobitno) values (1000 , true);
insert into lifce(id_lifce,dobitno) values (1111 , false);
insert into lifce(id_lifce,dobitno) values (1211 , true);
insert into lifce(id_lifce,dobitno) values (1231 , true);
insert into lifce(id_lifce,dobitno) values (1251 , false);
insert into lifce(id_lifce,dobitno) values (1281 , true);

insert into tip(id_lifce,utakmica1,utakmica2,utakmica3,utakmica4,sistem,fiks,uplata) values (1000,'Barcelona-RealMadrid' , 'Dortmund-Krasnodar','Porto-Benfica','Barcelona-Kiddermeister','2/4','sis',50) and insert into lifce(tip1,tip2,tip3,tip4) values ()

insert into tip(id_lifce,utakmica1,utakmica2,utakmica3,utakmica4,sistem,fiks,uplata) values (1111,'Barcelona-RealMadrid' , 'Dortmund-Krasnodar','Porto-Krasnodar','Barcelona-Kiddermeister','2/4','sis',50)
insert into lifce(id_lifce,dobitno,tip1,tip2,tip3,tip4) values (1111,false,'1' , 'X' , '2' , 'X');

delete from lifce where id_lifce=1111;

update tip
set tip = '1'
where id_lifce = 1000

alter table tip
add column tip varchar(50);

alter table lifce
drop column uplata;

select * from lifce;

select * from ima;

select * from natprevar;

select * from tabela;


insert into lifce(id_lifce,dobitno) values (1111,true);

select * from tabela;

drop view prosekGoals;

--Prosek golovi na domasen tim , so cel da se doznae statistikata kolku doma dava golovi so ogled na kolku utakmici ima odigrano. Vo slucajov benfica ima izigrano 2 utakmici ima daeno 49 golovi , prosekot e 24 i slednata utakmica im e so Benfica-Hansa Rostock,Benfica-Porto

create view prosekGoals as 
select s.golovi/s.br_natprevari as prosekDoma,n.tim1,n.tim2 from tim as s,natprevar as n
where s.ime = n.tim1
group by golovi,br_natprevari,n.tim1,n.tim2,prosekDoma
order by golovi desc;

select * from prosekGoals;

--Dokolku korisnikot saka da gi proveri lifcinjata kou shto mu se dobitni toj moze da go napravi toa so shto bi dobil koe id na lifce e i dali toa e dobitno

create view dobitni as
select s.id_lifce ,s.dobitno from lifce as s
where s.dobitno is true
group by id_lifce,dobitno
order by id_lifce

--Ne dobitni lifcinja koj shto gi ima korisnikot.

create view nedobitni as
select s.id_lifce ,s.dobitno from lifce as s
where s.dobitno is false
group by id_lifce,dobitno
order by id_lifce

-- Prebaruvanje na site timovi koi igraat vo odredena liga, vo konkretniot sluchaj stanuva za Premier League.

create view timoviVoLiga as
select ime,liga from ima
where liga in (select liga from tabela where liga='Premier League');

--Prebrojuvanje na dobitni tiketi kaj odreden User.

create view brojNaTiketiDobitni as
select count(*) , dobitno from lifce
where dobitno = true
group by dobitno;

select * from brojNaTiketiDobitni;


--Dokolku korisnikot izbere odredeni granici za timovi, da kazheme vo slucajov izbira da mu se dadat timovite koi shto imaat poveke od 50 bodovi i poveke od 10 golovi
create view goloviIbodovi as
select ima.liga,ima.ime from ima
inner join tabela
on ima.liga = tabela.liga
inner join tim
on ima.ime = tim.ime
where bodovi > 60 and golovi > 20
group by ima.liga,ima.ime;

select avg(tabela.bodovi) from tabela;



select * from tabela;
select * from tim;
select * from ima;

select * from timoviVoLiga;
drop view timoviVoLiga;


