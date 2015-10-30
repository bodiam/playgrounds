This dir is not a part of the actual playground code.
It is here just to help me in future with initial Oracle schema set up before running Liquibase.
[`drop_create`](drop_create) is a configurable script to run [`drop_create.sql`](drop_create.sql) on various environments:

    LIQUIBASE_PLAYGROUND_DB_HOST=$(docker-machine ip default) ./drop_create
