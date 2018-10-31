# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tb_Condition (
  id                        varchar(255) not null,
  pro                       varchar(255),
  repair                    varchar(255),
  rate                      varchar(255),
  constraint pk_tb_Condition primary key (id))
;

create table tb_Contact (
  id                        varchar(255) not null,
  name                      varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  subject                   varchar(255),
  description               varchar(255),
  constraint pk_tb_Contact primary key (id))
;

create table tb_Homeimg (
  id                        varchar(255) not null,
  picture                   varchar(255),
  constraint pk_tb_Homeimg primary key (id))
;

create table tb_Modeorders (
  id                        varchar(255) not null,
  name                      varchar(255),
  picture                   varchar(255),
  constraint pk_tb_Modeorders primary key (id))
;

create table tb_Orders (
  id                        varchar(255) not null,
  date                      datetime,
  user_id                   varchar(255),
  status                    varchar(255),
  constraint pk_tb_Orders primary key (id))
;

create table tb_OrdersDetsil (
  id                        varchar(255) not null,
  orders_id                 varchar(255),
  product_id                varchar(255),
  amount                    integer,
  constraint pk_tb_OrdersDetsil primary key (id))
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

create table tb_Slip (
  id                        varchar(255) not null,
  slip_order                varchar(255),
  picture                   varchar(255),
  constraint pk_tb_Slip primary key (id))
;

create table tb_Types (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_tb_Types primary key (id))
;

create table tb_User (
  id                        varchar(255) not null,
  name                      varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  address                   varchar(255),
  phone                     varchar(255),
  status                    varchar(255),
  constraint pk_tb_User primary key (id))
;

alter table tb_Orders add constraint fk_tb_Orders_user_1 foreign key (user_id) references tb_User (id) on delete restrict on update restrict;
create index ix_tb_Orders_user_1 on tb_Orders (user_id);
alter table tb_OrdersDetsil add constraint fk_tb_OrdersDetsil_orders_2 foreign key (orders_id) references tb_Orders (id) on delete restrict on update restrict;
create index ix_tb_OrdersDetsil_orders_2 on tb_OrdersDetsil (orders_id);
alter table tb_OrdersDetsil add constraint fk_tb_OrdersDetsil_product_3 foreign key (product_id) references tb_Product (id) on delete restrict on update restrict;
create index ix_tb_OrdersDetsil_product_3 on tb_OrdersDetsil (product_id);
alter table tb_Product add constraint fk_tb_Product_types_4 foreign key (types_id) references tb_Types (id) on delete restrict on update restrict;
create index ix_tb_Product_types_4 on tb_Product (types_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tb_Condition;

drop table tb_Contact;

drop table tb_Homeimg;

drop table tb_Modeorders;

drop table tb_Orders;

drop table tb_OrdersDetsil;

drop table tb_Promotion;

drop table tb_Product;

drop table tb_Slip;

drop table tb_Types;

drop table tb_User;

SET FOREIGN_KEY_CHECKS=1;

