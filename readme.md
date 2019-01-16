# AGP Project

## Hibernate
### Requirements

- MySQL
- Database named agp
- utf8_bin encoding
### Notes
``hibernate.cfg.xml`` must be located in the resources folder due to the usage of maven.

Mirror dankest.space -> github



## Files organisation

```
main
└── ressources
    └── log4j2-test.xml			: Logger configuration
    └── hibernate.cfg.xml		: Hibernate configuration
    ├── index					: Lucene researchs results
   	│ 	└── ...
    └── description				: Touristic site descriptions
    	└── 1.txt
    	└── 2.txt

```

Touristic site descriptions file name are for now the primary key of the **touristic site** in the database.

This can be change by using the description attribute of the touristic site.