create table address
(
    id          bigint auto_increment
        primary key,
    district    varchar(255) null,
    floor       varchar(255) null,
    postal_code varchar(255) null,
    street      varchar(255) null,
    street_num  int          null,
    town        varchar(255) null
);

INSERT INTO address (id, district, floor, postal_code, street, street_num, town)
VALUES
    (1, 'district', 1, '45231', 'streetName', 3, 'town'),
    (2, 'whateverD', 4, '25482', 'odos', 3, 'perioxi');




create table applicant
(
    id            bigint auto_increment
        primary key,
    date_of_birth date null,
    email         varchar(255) null,
    name        varchar(255) not null,
    gender        enum ('FEMALE', 'MALE') null,
    surname        varchar(255) not null,
    mobile        varchar(255) null,
    nationality   enum ('AFGHAN', 'ALBANIAN', 'ALGERIAN', 'AMERICAN', 'ANDORRAN', 'ANGOLAN', 'ANGUILLAN', 'CITIZEN_OF_ANTIGUA_AND_BARBUDA', 'ARGENTINE', 'ARMENIAN', 'AUSTRALIAN', 'AUSTRIAN', 'AZERBAIJANI', 'BAHAMIAN', 'BAHRAINI', 'BANGLADESHI', 'BARBADIAN', 'BELARUSIAN', 'BELGIAN', 'BELIZEAN', 'BENINESE', 'BERMUDIAN', 'BHUTANESE', 'BOLIVIAN', 'CITIZEN_OF_BOSNIA_AND_HERZEGOVINA', 'BOTSWANAN', 'BRAZILIAN', 'BRITISH', 'BRITISH_VIRGIN_ISLANDER', 'BRUNEIAN', 'BULGARIAN', 'BURKINAN', 'BURMESE', 'BURUNDIAN', 'CAMBODIAN', 'CAMEROONIAN', 'CANADIAN', 'CAPE_VERDEAN', 'CAYMAN_ISLANDER', 'CENTRAL_AFRICAN', 'CHADIAN', 'CHILEAN', 'CHINESE', 'COLOMBIAN', 'COMORAN', 'CONGOLESE_CONGO', 'CONGOLESE_DRC', 'COOK_ISLANDER', 'COSTA_RICAN', 'CROATIAN', 'CUBAN', 'CYMRAES', 'CYMRO', 'CYPRIOT', 'CZECH', 'DANISH', 'DJIBOUTIAN', 'DOMINICAN', 'CITIZEN_OF_THE_DOMINICAN_REPUBLIC', 'DUTCH', 'EAST_TIMORESE', 'ECUADOREAN', 'EGYPTIAN', 'EMIRATI', 'ENGLISH', 'EQUATORIAL_GUINEAN', 'ERITREAN', 'ESTONIAN', 'ETHIOPIAN', 'FAROESE', 'FIJIAN', 'FILIPINO', 'FINNISH', 'FRENCH', 'GABONESE', 'GAMBIAN', 'GEORGIAN', 'GERMAN', 'GHANAIAN', 'GIBRALTARIAN', 'GREEK', 'GREENLANDIC', 'GRENADIAN', 'GUAMANIAN', 'GUATEMALAN', 'CITIZEN_OF_GUINEA_BISSAU', 'GUINEAN', 'GUYANESE', 'HAITIAN', 'HONDURAN', 'HONG_KONGER', 'HUNGARIAN', 'ICELANDIC', 'INDIAN', 'INDONESIAN', 'IRANIAN', 'IRAQI', 'IRISH', 'ISRAELI', 'ITALIAN', 'IVORIAN', 'JAMAICAN', 'JAPANESE', 'JORDANIAN', 'KAZAKH', 'KENYAN', 'KITTSIAN', 'CITIZEN_OF_KIRIBATI', 'KOSOVAN', 'KUWAITI', 'KYRGYZ', 'LAO', 'LATVIAN', 'LEBANESE', 'LIBERIAN', 'LIBYAN', 'LIECHTENSTEIN_CITIZEN', 'LITHUANIAN', 'LUXEMBOURGER', 'MACANESE', 'MACEDONIAN', 'MALAGASY', 'MALAWIAN', 'MALAYSIAN', 'MALDIVIAN', 'MALIAN', 'MALTESE', 'MARSHALLESE', 'MARTINIQUAIS', 'MAURITANIAN', 'MAURITIAN', 'MEXICAN', 'MICRONESIAN', 'MOLDOVAN', 'MONEGASQUE', 'MONGOLIAN', 'MONTENEGRIN', 'MONTSERRATIAN', 'MOROCCAN', 'MOSOTHO', 'MOZAMBICAN', 'NAMIBIAN', 'NAURUAN', 'NEPALESE', 'NEW_ZEALANDER', 'NICARAGUAN', 'NIGERIAN', 'NIGERIEN', 'NIUEAN', 'NORTH_KOREAN', 'NORTHERN_IRISH', 'NORWEGIAN', 'OMANI', 'PAKISTANI', 'PALAUAN', 'PALESTINIAN', 'PANAMANIAN', 'PAPUA_NEW_GUINEAN', 'PARAGUAYAN', 'PERUVIAN', 'PITCAIRN_ISLANDER', 'POLISH', 'PORTUGUESE', 'PRYDEINIG', 'PUERTO_RICAN', 'QATARI', 'ROMANIAN', 'RUSSIAN', 'RWANDAN', 'SALVADOREAN', 'SAMMARINESE', 'SAMOAN', 'SAO_TOMEAN', 'SAUDI_ARABIAN', 'SCOTTISH', 'SENEGALESE', 'SERBIAN', 'CITIZEN_OF_SEYCHELLES', 'SIERRA_LEONEAN', 'SINGAPOREAN', 'SLOVAK', 'SLOVENIAN', 'SOLOMON_ISLANDER', 'SOMALI', 'SOUTH_AFRICAN', 'SOUTH_KOREAN', 'SOUTH_SUDANESE', 'SPANISH', 'SRI_LANKAN', 'ST_HELENIAN', 'ST_LUCIAN', 'STATELESS', 'SUDANESE', 'SURINAMESE', 'SWAZI', 'SWEDISH', 'SWISS', 'SYRIAN', 'TAIWANESE', 'TAJIK', 'TANZANIAN', 'THAI', 'TOGOLESE', 'TONGAN', 'TRINIDADIAN', 'TRISTANIAN', 'TUNISIAN', 'TURKISH', 'TURKMEN', 'TURKS_AND_CAICOS_ISLANDER', 'TUVALUAN', 'UGANDAN', 'UKRAINIAN', 'URUGUAYAN', 'UZBEK', 'VATICAN_CITIZEN', 'CITIZEN_OF_VANUATU', 'VENEZUELAN', 'VIETNAMESE', 'VINCENTIAN', 'WALLISIAN', 'WELSH', 'YEMENI', 'ZAMBIAN', 'ZIMBABWEAN') null,
    occupation    enum ('STUDENT', 'CIVIL_SERVANT', 'COMPANY_EMPLOYEE', 'SELF_EMPLOYEED', 'HOMEMAKER', 'TEACHER', 'UNEMPLOYED', 'OTHER') null,
    reg_num       varchar(255) null,
    address_id    bigint null,
    level         enum ('BEGINNER', 'ADVANCED') null,

    constraint reg_num_UNIQUE
        unique (reg_num),
    constraint FKims4x7k35x7qeddjnunugfox6
        foreign key (address_id) references address (id)
);



INSERT INTO applicant (id, date_of_birth, email, name , gender, surname, mobile, nationality, occupation, reg_num, address_id,level)
VALUES
    (1, '2024-01-30 00:00:00', 'johndoe@example.com', 'John', 'MALE', 'Doe', '306981234567', 'AMERICAN', 'CIVIL_SERVANT', '0', 1,'BEGINNER'),
    (2, '2024-01-29 00:00:00', 'EwanS@example.com', 'Ewan', 'MALE', 'Smith', '306981234567', 'AMERICAN', 'CIVIL_SERVANT','1',  2,'ADVANCED'),
    (3, '2024-01-1 00:00:00', 'LiamS@example.com', 'Liam', 'MALE', 'Smith', '306981234567', 'AMERICAN', 'CIVIL_SERVANT','2',  2,'BEGINNER'),
    (4, '2024-01-3 00:00:00', 'maryq@example.com', 'Mary', 'FEMALE', 'Quinn', '306981234567', 'ENGLISH', 'TEACHER', '3', 1,'ADVANCED');


create table users
(
    username varchar(255) not null primary key,
    password varchar(255) not null,
    role enum('APPLICANT','ADMIN')

);

-- create table role
-- (
--     id   int auto_increment
--         primary key,
--     name varchar(255) not null
-- );

INSERT INTO users(username, password,role) VALUES ('peter','{noop}peter123','APPLICANT'),('admin','{noop}admin123','ADMIN'),('manager','{noop}manager123','ADMIN');
-- INSERT INTO role(id, name) VALUES (1,'APPLICANT'),(2,'ADMIN');


create table authorities
(
    authority VARCHAR(50) NOT NULL ,
    username VARCHAR(255) not null,
 foreign key (`username`) references users(`username`)


);


INSERT INTO authorities (username,authority) VALUES ('peter','ROLE_APPLICANT');
INSERT INTO authorities (username,authority) VALUES('admin','ROLE_ADMIN');
INSERT INTO authorities (username,authority) VALUES('manager','ROLE_APPLICANT'),('manager','ROLE_ADMIN');


