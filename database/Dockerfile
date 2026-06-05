FROM mcr.microsoft.com/mssql-tools

WORKDIR /scripts

COPY init.sql .
COPY entrypoint.sh .

RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
