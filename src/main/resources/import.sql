INSERT INTO account (username, password, email, recruter_id, candidate_id) VALUES ('zelkotb','password','zelkot@gmail.com', null, 1);
INSERT INTO account (username, password, email, recruter_id, candidate_id) VALUES ('yattaki','password','yattaki@gmail.com', 2, null);

INSERT INTO application (date,application_status,candidate,offer) VALUE ('2019-12-01','WAITING',1,1);

INSERT INTO candidate (name,last_name,phone_number,birthday,address,image_path,cv_path,nationality,situation,profil,city) VALUE ('zakaria','el kotb','0678493874','1996-10-13','hay el farah','zakaria','zakaria','MOROCCAN','SINGLE','BANKING',1);

INSERT INTO city (city,country) VALUES ('CASABLANCA',1),('RABAT',2);

INSERT INTO company (name,city) VALUES ('ATOS',1),('Capgmini',2);

INSERT INTO country (country) VALUE ('MOROCCO');

INSERT INTO experience (title,begin_date,end_date,company,candidate) VALUE ('FullStack developer','2019-09-01',null,1,1);

INSERT INTO formation (begin_date,end_date,formation_type,description,diploma,school,candidate) VALUE ('2017-09-01','2019-06-20','COMPUTERSCIENCE','nothing mush to say','ENGINEER',1,1);

INSERT INTO hr (name,last_name,phone_number,birthday,address,image_path,cv_path,nationality,situation,city) VALUE ('yassine','attaki','0645663722','1996-06-18','hay el mohammadi','yassine','yassine','MOROCCAN','SINGLE',1);

INSERT INTO language (name) VALUES ('ARABIC'),('FRENCH'),('ENGLISH');

INSERT INTO mission (title,description,experience) VALUE ('projet SPRINT','automatisation des tests non regression avec Selenium',1);

INSERT INTO offer (title,description,profil,recruter,company,city) VALUE ('Developpeur fullStack js','on est a la recherche d"un développeur FullStavk pour une mission pour notre client ABB','BANKING',2,1,1);

INSERT INTO role (role) VALUES ('recruter'),('candidate'),('admin');

INSERT INTO school (name,school_type,city) VALUE ('INPT','INSTITUTE',1);

INSERT INTO skill (name) VALUES ('java'),('spring'),('angular'),('sql'),('jenkins');

INSERT INTO account_role (account_id,role_id) values (1,1),(2,2);

INSERT INTO candidate_skill(candidate_id,skill_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5);

INSERT INTO candidate_language(candidate_id,language_id) VALUES (1,1),(1,2),(1,3);

INSERT INTO recruter_language(recruter_id,language_id) VALUES (2,1),(2,2),(2,3);

INSERT INTO offer_skill(offer_id,skill_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5);

