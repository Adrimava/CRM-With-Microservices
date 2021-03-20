drop schema if exists contact;
create schema contact;
use contact;

create table contact (
   id int auto_increment not null,
    name varchar(255),
    phone_number varchar(255),
    email varchar(255),
    company_name varchar(255),
    primary key (id)
);
insert into contact (name, phone_number, email, company_name) values
("Pedro", "684253247", "pedro@pica.piedra", "Canteras Rodriguez"),
("Jaime", "628242447", "jaimelbueno@bond.es", "Royal British Crown"),
("Pepe", "897425631", "pepebotellas@bot.es", "Embotelladora Pepito");