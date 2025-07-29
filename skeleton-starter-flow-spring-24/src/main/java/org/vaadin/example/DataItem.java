package org.vaadin.example;

public class DataItem {
    private String id;
    private String schema;
    private String name;
    private String aliases;
    private String birthDate;
    private String country;
    private String addresses;
    private String identifiers;
    private String sanctions;
    private String phones;
    private String emails;
    private String dataset;
    private String firstSeen;
    private String lastSeen;
    private String lastChange; 
    private int levenshteinScore;
    private int phoneticScore;
    private int totalScore;
    private String database = "PEP"; 

    public DataItem() {
    }

    public DataItem(String id, String schema, String name, String aliases, String birthDate, String country, 
                   String addresses, String identifiers, String sanctions, String phones, String emails, 
                   String dataset, String firstSeen, String lastSeen, String lastChange, int levenshteinScore, 
                   int phoneticScore, int totalScore, String database) {
        this.id = id;
        this.schema = schema;
        this.name = name;
        this.aliases = aliases;
        this.birthDate = birthDate;
        this.country = country;
        this.addresses = addresses;
        this.identifiers = identifiers;
        this.sanctions = sanctions;
        this.phones = phones;
        this.emails = emails;
        this.dataset = dataset;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
        this.lastChange = lastChange;
        this.levenshteinScore = levenshteinScore;
        this.phoneticScore = phoneticScore;
        this.totalScore = totalScore;
        this.database = database;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSchema() { return schema; }
    public void setSchema(String schema) { this.schema = schema; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAliases() { return aliases; }
    public void setAliases(String aliases) { this.aliases = aliases; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getAddresses() { return addresses; }
    public void setAddresses(String addresses) { this.addresses = addresses; }

    public String getIdentifiers() { return identifiers; }
    public void setIdentifiers(String identifiers) { this.identifiers = identifiers; }

    public String getSanctions() { return sanctions; }
    public void setSanctions(String sanctions) { this.sanctions = sanctions; }

    public String getPhones() { return phones; }
    public void setPhones(String phones) { this.phones = phones; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public String getDataset() { return dataset; }
    public void setDataset(String dataset) { this.dataset = dataset; }

    public String getFirstSeen() { return firstSeen; }
    public void setFirstSeen(String firstSeen) { this.firstSeen = firstSeen; }

    public String getLastSeen() { return lastSeen; }
    public void setLastSeen(String lastSeen) { this.lastSeen = lastSeen; }

    public String getLastChange() { return lastChange; }
    public void setLastChange(String lastChange) { this.lastChange = lastChange; }

    public int getLevenshteinScore() { return levenshteinScore; }
    public void setLevenshteinScore(int levenshteinScore) { this.levenshteinScore = levenshteinScore; }

    public int getPhoneticScore() { return phoneticScore; }
    public void setPhoneticScore(int phoneticScore) { this.phoneticScore = phoneticScore; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

    public String getDatabase() { return database; }
    public void setDatabase(String database) { this.database = database; }
}
