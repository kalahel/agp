# AGP Project
## Project setup steps
### PATH
Before any launch you need to setup absolute path to your description folder (see Files organisation).
In :
```
src
└── main
    └── java
        └── com
            └── ucp
                └── configuration
                    └── ConfigurationEntry
```
Set both static variable with the location of your description folder and index folder (found into the resources folder).
Index folder will not exist before first launch, this is not a problem, enter it as it will be created.

Example of path :
- Description : `C:\Fac\M1\COO\agp\src\main\resources\descriptions`
- Index : `C:\Fac\M1\COO\agp\src\main\resources\index`

The necessity to absolute path over relative path, is the difference in path when the program is started with or without tomcat.

Example :
- Without Tomcat : `D:/Bureau/agp/target/classes/index`
- With Tomcat : `D:/Bureau/agp/classes/artifacts/agp_war_exploded/WEB-INF/classes/index/`

### Lucene indexation
Launch `LuceneLauncher` main method to index descriptions folder.

### Filling database

#### Requirements

- MySQL
- Database named "agp"
- utf8_bin encoding

Launch `hibernate/DatabaseFiller` main method to populate the database.

``hibernate.cfg.xml`` must be located in the resources folder due to the usage of maven.

## Tomcat Server

To start the web user interface create an artifact with the project and launch a tomcat server.

## Files organization

```
main
└── ressources
    └── log4j2-test.xml			: Logger configuration
    └── hibernate.cfg.xml		: Hibernate configuration
    ├── index					: Lucene researchs results (created after firt luceneLauncher run)
   	│ 	└── ...
    └── description				: Touristic site descriptions
    	└── 1.txt
    	└── 2.txt
    	└── ...

```

Touristic site descriptions file name are for now the primary key of the **touristic site** in the database.

This can be change by using the description attribute of the touristic site.