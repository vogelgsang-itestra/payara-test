CREATE TABLE entity_history(    id         int8         NOT NULL,    created    timestamp    NULL,    modified   timestamp    NULL,    value      varchar(255) NULL,    valid_from timestamp    not null,    valid_to   timestamp    null,    CONSTRAINT entity_history_unique unique (id, valid_from, valid_to));

CREATE TABLE child_entity_history(    id         int8         NOT NULL,    created    timestamp    NULL,    modified   timestamp    NULL,    value      varchar(255) NULL,    valid_from timestamp    not null,    valid_to   timestamp    null,    CONSTRAINT child_entity_history_unique unique (id, valid_from, valid_to));

CREATE TABLE grand_child_entity_history(    id         int8         NOT NULL,    created    timestamp    NULL,    modified   timestamp    NULL,    value      varchar(255) NULL,    valid_from timestamp    not null,    valid_to   timestamp    null,    CONSTRAINT grand_child_entity_history_unique unique (id, valid_from, valid_to));

CREATE TABLE child_assignment_history(    id              int8      NOT NULL,    created         timestamp NULL,    modified        timestamp NULL,    entity_id       int8      NULL,    child_entity_id int8      NULL,    valid_from      timestamp not null,    valid_to        timestamp null,    CONSTRAINT child_assignment_history_unique unique (id, valid_from, valid_to));

CREATE TABLE grand_child_assignment_history(    id                    int8      NOT NULL,    created               timestamp NULL,    modified              timestamp NULL,    child_assignment_id   int8      NULL,    grand_child_entity_id int8      NULL,    valid_from            timestamp not null,    valid_to              timestamp null,    CONSTRAINT grand_child_assignment_history_unique unique (id, valid_from, valid_to));
