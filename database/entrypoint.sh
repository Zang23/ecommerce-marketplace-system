#!/bin/bash

echo "Aguardando SQL Server iniciar..."

until /opt/mssql-tools/bin/sqlcmd \
-S sqlserver \
-U sa \
-P "$MSSQL_SA_PASSWORD" \
-Q "SELECT 1" > /dev/null 2>&1
do
  sleep 2
done

echo "SQL Server pronto."


/opt/mssql-tools/bin/sqlcmd \
-S sqlserver \
-U sa \
-P "$MSSQL_SA_PASSWORD" \
-i /scripts/init.sql


echo "Banco inicializado."
