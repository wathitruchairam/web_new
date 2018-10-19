# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tb_Homeimg (
  id                        varchar(255) not null,
  picture                   varchar(255),
  constraint pk_tb_Homeimg primary key (id))
;

create table tb_Promotion (
  id                        varchar(255) not null,
  picture                   varchar(255),
  constraint pk_tb_Promotion primary key (id))
;

create table tb_Product (
  id                        varchar(255) not null,
  name                      varchar(255),
  brand                     varchar(255),
  detail                    varchar(255),
  amount                    double,
  price                     double,
  picture                   varchar(255),
  types_id                  varchar(255),
  constraint pk_tb_Product primary key (id))
;

create table tb_Types (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_tb_Types primary key (id))
;

create table tb_User (
  id                        varchar(255) not null,
  name                      varchar(255),
  status                    varchar(255),
  password                  varchar(255),
  constraint pk_tb_User primary key (id))
;

alter table tb_Product add constraint fk_tb_Product_types_1 foreign key (types_id) references tb_Types (id) on delete restrict on update restrict;
create index ix_tb_Product_types_1 on tb_Product (types_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tb_Homeimg;

drop table tb_Promotion;

drop table tb_Product;

drop table tb_Types;

drop table tb_User;

SET FOREIGN_KEY_CHECKS=1;

