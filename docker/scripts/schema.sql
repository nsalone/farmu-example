create table cities
(
    id         char(36)   default (uuid())          not null
        primary key,
    name       varchar(20)                          not null,
    active     tinyint(1) default 1                 not null,
    created_at timestamp  default CURRENT_TIMESTAMP not null,
    updated_at timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    collate = utf8mb4_general_ci;

create table companies
(
    id         char(36)   default (uuid())          not null
        primary key,
    user_id    char(36)                             null comment 'Company owner',
    name       varchar(50)                          not null,
    id_type    enum ('1')                           not null comment 'Company identification type (1=nit)',
    id_number  varchar(20)                          not null comment 'Number that corresponds to the type of identification of the company',
    active     tinyint(1) default 1                 not null,
    created_at timestamp  default CURRENT_TIMESTAMP not null,
    updated_at timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
    collate = utf8mb4_general_ci;

create table drugstores
(
    id               char(36)   default (uuid())          not null
        primary key,
    company_id       char(36)                             not null,
    city_id          char(36)                             not null,
    name             varchar(50)                          not null,
    address          varchar(100)                         not null,
    type_association enum ('INDEPENDENT', 'PITAYA')       not null,
    active           tinyint(1) default 1                 not null,
    created_at       timestamp  default CURRENT_TIMESTAMP not null,
    updated_at       timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint drugstores_ibfk_1
        foreign key (company_id) references companies (id),
    constraint drugstores_ibfk_2
        foreign key (city_id) references cities (id)
)
    collate = utf8mb4_general_ci;

create index city_id
    on drugstores (city_id);

create index company_id
    on drugstores (company_id);

create table order_details
(
    id                      char(36)     default (uuid())          not null
        primary key,
    order_id                char(36)                               not null,
    product_id              char(36)     default ''                not null,
    company_id              char(36)                               not null,
    product_presentation_id char(36)     default ''                null,
    concept                 varchar(100) default ''                null,
    quantity                int                                    not null,
    subtotal                float(10, 2)                           not null comment 'Total sale price without applying discounts and taxes',
    percent_discount        float(10, 2)                           not null comment 'Percentage of the discount that is made per product',
    percent_iva             float(10, 2)                           not null,
    sale_price              float(10, 2)                           not null,
    free_sale               tinyint(1)   default 0                 null,
    active                  tinyint(1)   default 1                 not null,
    created_at              timestamp    default CURRENT_TIMESTAMP not null,
    updated_at              timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint order_details_ibfk_1
        foreign key (company_id) references companies (id)
)
    collate = utf8mb4_general_ci;

create table order_detail_histories
(
    id                      char(36)     default (uuid())          not null
        primary key,
    order_detail_id         char(36)                               not null,
    order_id                char(36)                               not null,
    product_id              char(36)                               not null,
    company_id              char(36)                               not null,
    product_presentation_id char(36)                               null,
    concept                 varchar(100) default ''                null,
    quantity                int                                    not null,
    subtotal                float(10, 2)                           not null comment 'Total sale price without applying discounts and taxes',
    percent_discount        float(10, 2)                           not null comment 'Percentage of the discount that is made per product',
    percent_iva             float(10, 2)                           not null,
    sale_price              float(10, 2)                           not null,
    free_sale               tinyint(1)   default 0                 null,
    active                  tinyint(1)   default 1                 not null,
    created_at              timestamp    default CURRENT_TIMESTAMP not null,
    constraint order_detail_histories_ibfk_1
        foreign key (company_id) references companies (id),
    constraint order_detail_histories_ibfk_2
        foreign key (order_detail_id) references order_details (id)
)
    collate = utf8mb4_general_ci;

create index company_id
    on order_detail_histories (company_id);

create index order_detail_id
    on order_detail_histories (order_detail_id);

create index company_id
    on order_details (company_id);

create table order_details_inventories
(
    order_id     char(36)                            not null,
    product_id   char(36)                            not null,
    inventory_id char(36)                            not null,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    primary key (order_id, product_id, inventory_id)
)
    collate = utf8mb4_general_ci;

create table products_farmu
(
    id          char(36)     default (uuid())          not null
        primary key,
    name        varchar(200)                           null,
    unit_sizes  int                                    null comment 'Minimum number of units that a product has',
    blister     tinyint(1)   default 0                 not null comment 'File image name',
    percent_iva float(3, 2)                            null,
    barcode     varchar(20)  default ''                null,
    image_path  varchar(255) default ''                null comment 'File image name',
    active      tinyint(1)   default 1                 not null,
    created_at  timestamp    default CURRENT_TIMESTAMP not null,
    updated_at  timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    sku         varchar(50)                            not null,
    constraint sku
        unique (sku)
)
    collate = utf8mb4_general_ci;

create table users
(
    id               char(36)   default (uuid())          not null
        primary key,
    company_id       char(36)                             not null,
    first_name       varchar(20)                          not null,
    second_name      varchar(20)                          not null,
    first_last_name  varchar(20)                          not null,
    second_last_name varchar(20)                          not null,
    email            varchar(50)                          not null,
    cellphone        varchar(10)                          not null,
    active           tinyint(1) default 1                 not null,
    created_at       timestamp  default CURRENT_TIMESTAMP not null,
    updated_at       timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint users_ibfk_1
        foreign key (company_id) references companies (id)
)
    collate = utf8mb4_general_ci;

create table external_device_connection_auth
(
    id                      char(36)   default (uuid())          not null
        primary key,
    action_by_user_id       char(36)                             not null,
    drugstore_id            char(36)                             not null,
    company_id              char(36)                             not null,
    websocket_connection_id varchar(20)                          not null comment 'id of the main websocket connection, where the user logged in',
    module                  enum ('POS')                         not null comment 'Pitayas module where the external connection was requested',
    was_code_scanned        tinyint(1) default 0                 not null,
    active                  tinyint(1) default 1                 not null,
    created_at              timestamp  default CURRENT_TIMESTAMP not null,
    updated_at              timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint external_device_connection_auth_ibfk_1
        foreign key (drugstore_id) references drugstores (id),
    constraint external_device_connection_auth_ibfk_2
        foreign key (action_by_user_id) references users (id),
    constraint external_device_connection_auth_ibfk_3
        foreign key (company_id) references companies (id)
)
    collate = utf8mb4_general_ci;

create index company_id
    on external_device_connection_auth (company_id);

create index drugstore_id
    on external_device_connection_auth (drugstore_id);

create index user_id
    on external_device_connection_auth (action_by_user_id);

create table orders
(
    id                char(36)   default (uuid())                   not null
        primary key,
    action_by_user_id char(36)                                      not null comment 'Record of the user who performs the action (CRUD)',
    drugstore_id      char(36)                                      not null,
    company_id        char(36)                                      not null,
    concept           enum ('FREE SALE', 'INVENTORY SALE', 'MIXED') not null comment 'type of sale made',
    subtotal          float(10, 2)                                  not null comment 'Total sale price without applying discounts and taxes',
    discount          float(10, 2)                                  not null comment 'Total discount value',
    amount            float(10, 2)                                  not null comment 'Price with which the pharmacist sold the product',
    payment_type      enum ('CASH', 'NEQUI/DAVIVIENDA')             not null,
    client            varchar(100)                                  not null,
    status_payment    enum ('PAID', 'TRUSTED')                      not null,
    active            tinyint(1) default 1                          not null,
    created_at        timestamp  default CURRENT_TIMESTAMP          not null,
    updated_at        timestamp  default CURRENT_TIMESTAMP          not null on update CURRENT_TIMESTAMP,
    constraint orders_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint orders_ibfk_2
        foreign key (drugstore_id) references drugstores (id),
    constraint orders_ibfk_3
        foreign key (company_id) references companies (id)
)
    collate = utf8mb4_general_ci;

create table order_histories
(
    id                char(36)   default (uuid())                   not null
        primary key,
    order_id          char(36)                                      not null,
    action_by_user_id char(36)                                      not null comment 'Record of the user who performs the action (CRUD)',
    drugstore_id      char(36)                                      not null,
    company_id        char(36)                                      not null,
    concept           enum ('FREE SALE', 'INVENTORY SALE', 'MIXED') not null comment 'type of sale made',
    subtotal          float(10, 2)                                  not null comment 'Total sale price without applying discounts and taxes',
    discount          float(10, 2)                                  not null comment 'Total discount value',
    amount            float(10, 2)                                  not null comment 'Price with which the pharmacist sold the product',
    payment_type      enum ('CASH', 'NEQUI/DAVIVIENDA')             not null,
    client            varchar(100)                                  not null,
    status_payment    enum ('PAID', 'TRUSTED')                      not null,
    active            tinyint(1) default 1                          not null,
    created_at        timestamp  default CURRENT_TIMESTAMP          not null,
    constraint order_histories_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint order_histories_ibfk_2
        foreign key (drugstore_id) references drugstores (id),
    constraint order_histories_ibfk_3
        foreign key (company_id) references companies (id),
    constraint order_histories_ibfk_4
        foreign key (order_id) references orders (id)
)
    collate = utf8mb4_general_ci;

create index action_by_user_id
    on order_histories (action_by_user_id);

create index company_id
    on order_histories (company_id);

create index drugstore_id
    on order_histories (drugstore_id);

create index order_id
    on order_histories (order_id);

create index action_by_user_id
    on orders (action_by_user_id);

create index company_id
    on orders (company_id);

create index drugstore_id
    on orders (drugstore_id);

create table products
(
    id                char(36)                      default (uuid())          not null
        primary key,
    action_by_user_id char(36)                                                not null comment 'Record of the user who performs the action (CRUD)',
    drugstore_id      char(36)                                                not null,
    company_id        char(36)                                                not null,
    product_farmu_id  char(36)                      default ''                null,
    name              varchar(200)                  default ''                null,
    cost              float(10, 2)                                            not null comment 'Price with which the druggist bought the product',
    sale_price        float(10, 2)                                            not null comment 'Price with which the pharmacist sells the product',
    quantity          int                                                     not null comment 'Quantity of the product in its original presentation',
    unit_sizes        int                           default 0                 null comment 'Minimum number of units that a product has',
    blister           tinyint(1)                    default 0                 not null comment 'Boolean that indicates when a product has presentations',
    barcode           varchar(20)                   default ''                null,
    image_path        varchar(255)                  default ''                null comment 'File image name',
    type              enum ('OWN', 'FARMU')                                   not null,
    status            enum ('PENDING', 'FINALIZED') default 'FINALIZED'       null comment 'Current status of the product. When it is in a finalized state, it is because the product has all the necessary information to be able to sell it.',
    brand             varchar(20)                   default ''                null,
    active            tinyint(1)                    default 1                 not null,
    created_at        timestamp                     default CURRENT_TIMESTAMP not null,
    updated_at        timestamp                     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint products_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint products_ibfk_2
        foreign key (drugstore_id) references drugstores (id),
    constraint products_ibfk_3
        foreign key (company_id) references companies (id)
)
    collate = utf8mb4_general_ci;

create table inventories
(
    id                char(36)   default (uuid())          not null
        primary key,
    action_by_user_id char(36)                             not null comment 'Record of the user who performs the action (CRUD)',
    product_id        char(36)                             not null,
    company_id        char(36)                             not null,
    order_number_sku  varchar(50)                          null,
    cost              float(10, 2)                         not null comment 'Price with which the druggist bought the product',
    lot               int        default 0                 null,
    initial_quantity  int                                  not null comment 'Quantity that was entered in the lot of the product in its original presentation',
    actually_quantity int                                  not null comment 'Actual quantity in the lot of the product in its original presentation',
    active            tinyint(1) default 1                 not null,
    expiration_date   timestamp  default CURRENT_TIMESTAMP not null,
    created_at        timestamp  default CURRENT_TIMESTAMP not null,
    updated_at        timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint inventories_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint inventories_ibfk_2
        foreign key (company_id) references companies (id),
    constraint inventories_ibfk_3
        foreign key (product_id) references products (id)
)
    collate = utf8mb4_general_ci;

create index action_by_user_id
    on inventories (action_by_user_id);

create index company_id
    on inventories (company_id);

create index product_id
    on inventories (product_id);

create table product_histories
(
    id                char(36)   default (uuid())          not null
        primary key,
    product_id        char(36)                             not null,
    action_by_user_id char(36)                             not null comment 'Record of the user who performs the action (CRUD)',
    drugstore_id      char(36)                             not null,
    company_id        char(36)                             not null,
    name              varchar(50)                          not null,
    cost              float(10, 2)                         not null comment 'Price with which the druggist bought the product',
    sale_price        float(10, 2)                         not null comment 'Price with which the pharmacist sells the product',
    quantity          int                                  not null comment 'Quantity of the product in its original presentation',
    unit_sizes        int                                  null comment 'Minimum number of units that a product has',
    blister           tinyint(1) default 0                 not null comment 'Boolean that indicates when a product has presentations',
    barcode           varchar(20)                          not null,
    image_path        varchar(255)                         not null comment 'File image name',
    type              enum ('OWN', 'FARMU')                not null,
    brand             varchar(20)                          null,
    active            tinyint(1) default 1                 not null,
    created_at        timestamp  default CURRENT_TIMESTAMP not null,
    constraint product_histories_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint product_histories_ibfk_2
        foreign key (drugstore_id) references drugstores (id),
    constraint product_histories_ibfk_3
        foreign key (company_id) references companies (id),
    constraint product_histories_ibfk_4
        foreign key (product_id) references products (id)
)
    collate = utf8mb4_general_ci;

create index action_by_user_id
    on product_histories (action_by_user_id);

create index company_id
    on product_histories (company_id);

create index drugstore_id
    on product_histories (drugstore_id);

create index product_id
    on product_histories (product_id);

create table product_presentations
(
    id                char(36)   default (uuid())          not null
        primary key,
    action_by_user_id char(36)                             not null comment 'Record of the user who performs the action (CRUD)',
    product_id        char(36)                             not null,
    name              varchar(50)                          not null,
    unit_sizes        int                                  not null comment 'Minimum number of units that a presentation product has',
    sale_price        float(10, 2)                         not null comment 'Price with which the pharmacist sells the product',
    active            tinyint(1) default 1                 not null,
    created_at        timestamp  default CURRENT_TIMESTAMP not null,
    updated_at        timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint product_presentations_ibfk_1
        foreign key (action_by_user_id) references users (id),
    constraint product_presentations_ibfk_2
        foreign key (product_id) references products (id)
)
    collate = utf8mb4_general_ci;

create index action_by_user_id
    on product_presentations (action_by_user_id);

create index product_id
    on product_presentations (product_id);

create index action_by_user_id
    on products (action_by_user_id);

create index company_id
    on products (company_id);

create index drugstore_id
    on products (drugstore_id);

create index company_id
    on users (company_id);

create table users_drugstores
(
    user_id      char(36)                             not null,
    drugstore_id char(36)                             not null,
    active       tinyint(1) default 1                 not null,
    created_at   timestamp  default CURRENT_TIMESTAMP not null,
    updated_at   timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    primary key (user_id, drugstore_id),
    constraint users_drugstores_ibfk_1
        foreign key (user_id) references users (id),
    constraint users_drugstores_ibfk_2
        foreign key (drugstore_id) references drugstores (id)
)
    collate = utf8mb4_general_ci;

create index drugstore_id
    on users_drugstores (drugstore_id);

