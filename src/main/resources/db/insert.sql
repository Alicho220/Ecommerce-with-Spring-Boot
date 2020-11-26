SET FOREIGN_KEY_CHECKS = 0;

truncate table customer;
truncate table address;
truncate table customer_addresses;
truncate table card;
truncate table product;

insert into address(`id`, `city`, `country`, `state`, `street`, `zipcode`)
value (1, "Yaba", "Nigeria", "Lagos", "312 Herbert Marculay way, Sabo","100110"),
      (2, "Mushin", "Nigeria", "Lagos", "19 wey street, Mushin","001001");

insert into customer(`id`, `contact`, `email`, `first_name`,`last_name`,`password`)
value (1, "O903134533","jimmyoalicho@gmail.com","Tony","Alicho","alicho123"),
      (2, "07036565445","alichotony@gmail.com","Love","Ernest","alicho123");

insert into customer_addresses(`customer_id`,`addresses_id`)
values (1, 1),
       (1, 2),
       (2, 2);

insert into card(`id`, `name`, `type`, `cvv`, `exp_date`, `number`, `customer_id`)
values(1, "Okorie Chukwuemeka Alicho","master", 322,"12-10-21","5863937353729109",1),
(2, "Okorie Kelechi Alicho","visa", 233,"12-10-22","6563937353729109",null);


insert into product (`id`, `name`, `description`, `price`, `quantity`, `exp_date`)
values(1, "Garlic", "garlic is sweet", 50.0, 4, "10-10-21"),
      (2, "Tomato", "Tomato is Red", 80.0, 5, "10-10-22");

set foreign_key_checks =1;



