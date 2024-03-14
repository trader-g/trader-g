sleep 30s

/opt/mssql-tools/bin/sqlcmd -S beantraderdb.cidtxn2ndtwc.eu-west-1.rds.amazonaws.com -U keeganoreilly -P Dockerpassword -d master -i /scripts/init.sql
