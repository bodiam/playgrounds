## Oracle stuff

After `LIQUIBASE_PLAYGROUND` user has been created [Liquibase](http://www.liquibase.org/) can be used for further configurations.
There is also a wrapper script [`base`](base) to help set up connection details.
Changelogs should be aggregated in `changelog.xml`.
Just run `./base update` to get the latest DB version.
