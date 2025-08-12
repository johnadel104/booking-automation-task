# Booking.com Automation (Selenium + TestNG + Maven)

## Requirements
- JDK 11+
- Maven 3.8+
- Google Chrome (matches installed version)

## How to Run
1) Open project in IntelliJ (or use terminal).
2) Run:
   mvn clean test
   أو شغّل testng.xml من IntelliJ.

## Structure
src
└─ test
└─ java
├─ base
│   └─ BaseTest.java
├─ pages
│   ├─ HomePage.java
│   ├─ SearchResultsPage.java
│   ├─ HotelDetailsPage.java
│   └─ ReservationPage.java
└─ Tests
└─ SanityTest.java
testng.xml
pom.xml

## What the test does
- Search Alexandria
- Pick dates (1 Oct 2024 → 14 Oct 2024)
- Open Tolip Hotel Alexandria (first/second page)
- Assert dates on details page
- Assert hotel name on reservation page05