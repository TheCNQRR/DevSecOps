CREATE TABLE employee
(
    id                             UUID PRIMARY KEY,

    gender                         VARCHAR(10) CHECK ( gender IN ('female', 'male') ),

    name_title                     VARCHAR(10),
    name_first                     VARCHAR(50) NOT NULL,
    name_last                      VARCHAR(50) NOT NULL,

    location_street_number         INT,
    location_street_name           TEXT,
    location_city                  TEXT,
    location_state                 TEXT,
    location_country               TEXT,
    location_postcode              TEXT,
    location_coordinates_latitude  VARCHAR(50),
    location_coordinates_longitude VARCHAR(50),
    location_timezone_offset       VARCHAR(10),
    location_timezone_description  TEXT,

    email                          VARCHAR(50) NOT NULL UNIQUE,

    login_uuid                     UUID        NOT NULL UNIQUE,
    login_username                 TEXT        NOT NULL UNIQUE,
    login_password                 TEXT        NOT NULL,
    login_salt                     TEXT,
    login_md5                      TEXT,
    login_sha1                     TEXT,
    login_sha256                   TEXT,

    dob_date                       TIMESTAMP WITH TIME ZONE,
    dob_age                        INT,

    registered_date                TIMESTAMP WITH TIME ZONE,
    registered_age                 INT,

    phone                          VARCHAR(50),

    cell                           VARCHAR(50),

    id_info_name                   TEXT,
    id_info_value                  TEXT,

    picture_large                  TEXT,
    picture_medium                 TEXT,
    picture_thumbnail              TEXT,

    nat                            TEXT,

    CONSTRAINT unique_full_name UNIQUE (name_first, name_last)
);

CREATE TABLE IF NOT EXISTS outbox_event
(
    id             UUID PRIMARY KEY,
    aggregate_type VARCHAR(50) NOT NULL,
    aggregate_id   UUID        NOT NULL,
    payload        TEXT        NOT NULL,
    published      BOOLEAN     NOT NULL DEFAULT FALSE,
    created_at     TIMESTAMP   NOT NULL
);