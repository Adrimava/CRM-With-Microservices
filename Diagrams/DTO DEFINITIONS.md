# DTO DEFINITIONS

## LeadDto

```java
private Integer id;
@NotEmpty
private String name;
@NotEmpty
private String phoneNumber;
@NotEmpty
private String email;
@NotEmpty
private String companyName;
@NotNull
private Integer salesRepId;
```



## ConvertOpportunityDto

```java
@NotNull
private Integer leadId;
@NotEmpty
private String product;
@NotNull
private Integer quantity;
private Integer accountId;
private String industry;
private Integer employeeCount;
private String city;
private String country;
```



## CloseOpportunityDto

```java
@NotNull
private Integer opportunityId;
@NotEmpty
private String status;
```



## SalesRepDto

```java
private Integer id;
@NotEmpty
private String name;
```



##  AccountDto

```java
private Integer id;
@NotEmpty
private String industry;
@NotNull
private Integer employeeCount;
@NotEmpty
private String city;
@NotEmpty
private String country;
@NotEmpty
private List<Integer> contactList;
@NotEmpty
private List<Integer> opportunityList;
```



## UpdateAccountDto

```java
@NotNull
private Integer accountId;
@NotNull
private Integer contactId;
@NotNull
private Integer opportunityId;
```



## ContactDto

```java
private Integer id;
@NotEmpty
private String name;
@NotEmpty
private String phoneNumber;
@NotEmpty
private String email;
@NotEmpty
private String companyName;
```























