insert into avio_company (id,name,address_id,description,average_rating) values (1,'AirSerbia',111,'etihad uzo', 2.0);

insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id) values (1,'1 januar', '2 januar', 120, 2000, 300, 1, 2, 1);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id) values (2,'1 jun', '3 jun', 111, 3500, 350, 2, 3, 1);

insert into hotel (id, name, address_id, description) values (1,'Hotel Park', 1, 'Imamo i kladionicu');
insert into hotel (id, name, address_id, description) values (2,'Hotel Vojvodina', 2, 'Nemamo ni kladionicu');

insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (1,'Cistimo krevete', 99.99, 'A mozete i sami',1);

insert into hotel_room (id, number, free, hotel_id) values (1,'106', true,1);

insert into rent_car (id, name, address_id, description) values (1,'Mica rentakar', 1, 'Imamo sve.');

insert into rent_car_branch (id, name,address_id, rent_car_id) values (1,'Micin brenc', 1 , 1);

insert into rent_car_menu_item (id, service_name, price, description) values (1,'Pranje kola', 200,'Opis pranja kola');

insert into user (id, username, password, email, name, last_name, city, telephone_number, role) values (1,'DavidJuzniVetar', 'BugarskaBrena','melegi@gmail.com','David','Melegi','Zrenjanin','06334321',2);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role) values (2,'Rodja', 'rodja','rodja@gmail.com','Dusan','Radj','Zrenjanin','060123123',1);

insert into vehicle (id, type, seats_number, name, mark, model, year_produced, free, rent_car_branch_id, return_place_id)  values (1,2, 5,'Auto paja','A4','Audi',2010,true, 1, 1);

insert into address(id, country, city, postal_code, street, number) values (1,'Srbija', 'Beograd',11000, 'Juzni Bulevar', 2);