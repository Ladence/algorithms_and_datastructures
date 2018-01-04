package ru.ladence.datastructures;

public class Runner {
    public static void main(String[] args) {
        HashTable<String, String> hashTable = new HashTable<>();
        try {
            hashTable.add("Violate", "Yes");
            hashTable.add("Lol", "No");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(hashTable.size());
    }
}
