insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (21,'David', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','melegi@gmail.com','David','Melegi','Zrenjanin','06334321',2, true,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (22,'Rodja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','rodja@gmail.com','Dusan','Radj','Zrenjanin','060123123',1, true,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (23,'Kumara', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','kum@gmail.com','Aleksandar','Joksimovic','Leskovac','060123123',0, true,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (24,'Pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','pera@gmail.com','Pera','Petrovic','Beograd','060123123',0, true,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (25,'Zika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','zika@gmail.com','Zika','Zivkovic','Beograd','060123123',1, true,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, activated_account,last_password_reset_date,enabled) values (26,'Laza', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','laza@gmail.com','Laza','Lazarevic','Beograd','060123123',1, true,'2017-08-19 12:17:55',true);

insert into authority (id, name) values (1, 'REGISTERED_USER');
insert into authority (id, name) values (2, 'SERVICE_ADMIN');

insert into user_authority (user_id, authority_id) VALUES (21, 1);
insert into user_authority (user_id, authority_id) VALUES (22, 1);
insert into user_authority (user_id, authority_id) VALUES (22, 2);

insert into friendship (id, accepted, user1_id, user2_id) values (1, true, 21, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (2, true, 21, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (3, true, 21, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (4, true, 21, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (5, true, 21, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (6, true, 22, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (7, true, 22, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (8, true, 22, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (9, true, 22, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (10, true, 22, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (11, true, 23, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (12, true, 23, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (13, true, 23, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (14, true, 23, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (15, true, 23, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (16, true, 24, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (17, true, 24, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (18, true, 24, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (19, true, 24, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (20, true, 24, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (21, true, 25, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (22, true, 25, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (23, true, 25, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (24, true, 25, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (25, true, 25, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (26, true, 26, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (27, true, 26, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (28, true, 26, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (29, true, 26, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (30, true, 26, 25);


insert into avio_company (id,name,address_id,description,average_rating) values (101,'AirSerbia',101,'etihad uzo', 2.0);

insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id) values (101,'1 januar', '2 januar', 120, 2000, 300, 101, 102, 101);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id) values (102,'1 jun', '3 jun', 111, 3500, 350, 102, 101, 101);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id) values (103,'1 jun', '3 jun', 111, 3500, 350, 103, 103, 101);

insert into destinations (avio_company_id, address_id) values (101, 101);
insert into destinations (avio_company_id, address_id) values (101, 102);


insert into hotel (id, name, address_id, description) values (101,'Hotel Park', 101, 'Imamo i kladionicu');
insert into hotel (id, name, address_id, description) values (102,'Hotel Vojvodina', 102, 'Nemamo ni kladionicu');

insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (101,'Cistimo krevete', 99.99, 'A mozete i sami',102);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (102,'Mini bar', 25.00, 'Neki random opis', 102);

insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (101,'106', true, 101, 'opis prve sobe', 123.99, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (102,'107', true, 101, 'opis druge sobe', 100.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (103,'108', true, 101, 'opis trece sobe', 149.99, 2);

insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (104,'206', true, 102, 'opis prve sobe', 123.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (105,'207', true, 102, 'opis druge sobe', 250.00, 3);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (106,'208', true, 102, 'opis trece sobe', 150.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (107,'208', true, 102, 'opis cetvrte sobe', 150.00, 4);

insert into rent_car (id, name, address_id, description) values (101,'Mica rentakar', 101, 'Imamo sve.');

insert into rent_car_branch (id, name, address_id, rent_car_id) values (101, 'Micin brenc', 101, 101);

insert into rent_car_menu_item (id, service_name, price, description,rent_car_id) values (101,'Pranje kola', 200,'Opis pranja kola', 101);


insert into vehicle (id, type, seats_number, name, mark, model, year_produced, free, rent_car_id)  values (101, 0, 5, 'Auto paja', 'Audi', 'A4', 2010, true, 101);
insert into vehicle (id, type, seats_number, name, mark, model, year_produced, free, rent_car_id)  values (102, 1, 2, 'Auto paja', 'Aprilia', 'SR', 2010, true, 101);
insert into vehicle (id, type, seats_number, name, mark, model, year_produced, free, rent_car_id)  values (103, 2, 2, 'Auto paja', 'Suzuki', 'SX500', 2015, true, 101);

--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (101, '2019-01-21', '2019-01-15', 101, 101, 101, 21);
--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (102, '2019-01-21', '2019-01-15', 102, 101, 101, 22);
--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (103, '2019-02-15', '2019-02-01', 101, 101, 101, 22);

insert into address(id, country, city, postal_code, street, number) values (101,'Srbija', 'Beograd',11000, 'Juzni Bulevar', 2);
insert into address(id, country, city, postal_code, street, number) values (102,'Srbija', 'Novi Sad',21000, 'Ulica neka', 23);
insert into address(id, country, city, postal_code, street, number) values (103,'Srbija', 'Novi Sad',21000, 'Ulica neka', 33);