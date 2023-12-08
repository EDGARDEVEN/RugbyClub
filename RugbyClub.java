import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// Base class for Staff members
class Staff {
    private static int staffCount = 0;
    private String name;
    private int staffNumber;

    public Staff(String name) {
        this.name = name;
        this.staffNumber = ++staffCount;
    }

    public String getName() {
        return name;
    }

    public int getStaffNumber() {
        return staffNumber;
    }
}

// Club Staff
class ClubStaff extends Staff {
    private String type;

    public ClubStaff(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

// Coaching Staff
class CoachingStaff extends Staff {
    private int qualificationLevel;
    private String type;

    public CoachingStaff(String name, int qualificationLevel, String type) {
        super(name);
        this.qualificationLevel = qualificationLevel;
        this.type = type;
    }

    public int getQualificationLevel() {
        return qualificationLevel;
    }

    public String getType() {
        return type;
    }
}

// Player Class
class Player {
    private static int playerCount = 0;
    private String name;
    private int age;
    private int playerNumber;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.playerNumber = ++playerCount;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}

// Training Group
class TrainingGroup {
    private static int groupCount = 0;
    private String identifier;
    private int playerCount;
    private String trainingDay;
    private CoachingStaff coach;
    private List<Player> players;

    public TrainingGroup(String identifier, int playerCount, String trainingDay) {
        this.identifier = identifier;
        this.playerCount = playerCount;
        this.trainingDay = trainingDay;
        this.players = generatePlayers();
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public String getTrainingDay() {
        return trainingDay;
    }

    public CoachingStaff getCoach() {
        return coach;
    }

    public void setCoach(CoachingStaff coach) {
        this.coach = coach;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static String generateRandomName() {
        String[] names = {"Rodgers", "Kevin", "Jane", "Keziah", "Mark", "Kamau", "Otieno", "Junior", "Deven", "Eryka"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    private List<Player> generatePlayers() {
        Random random = new Random();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            String playerName = generateRandomName();
            int playerAge = random.nextInt(10) + 10; // Age between 10 and 19
            Player player = new Player(playerName, playerAge);
            playerList.add(player);
        }
        return playerList;
    }
}

public class RugbyClub {
    private static List<Staff> staffList = new ArrayList<>();
    private static List<CoachingStaff> coachingStaffList = new ArrayList<>();
    private static List<TrainingGroup> trainingGroups = new ArrayList<>();

    public static void main(String[] args) {
        // Generate initial data
        generateClubStaff();
        generateCoachingStaff();
        generateTrainingGroups();

        // Assign coaches to groups
        assignCoachesToGroups();

        // User interaction
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. List all staff");
            System.out.println("2. List staff by category");
            System.out.println("3. List all groups");
            System.out.println("4. List groups by training day");
            System.out.println("5. List players coached by a coach");
            System.out.println("6. List all players in the club");
            System.out.println("7. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        listAllStaff();
                        break;
                    case 2:
                        listStaffByCategory(scanner);
                        break;
                    case 3:
                        listAllGroups();
                        break;
                    case 4:
                        listGroupsByTrainingDay(scanner);
                        break;
                    case 5:
                        listPlayersByCoach(scanner);
                        break;
                    case 6:
                        listAllPlayers();
                        break;
                    case 7:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Function to generate Club staff
    private static void generateClubStaff() {
        Random random = new Random();
        String[] clubStaffTypes = {"GroundStaff", "Caretaker", "Physio"};

        for (int i = 0; i < 7; i++) {
            String name = generateRandomName();
            String type = clubStaffTypes[random.nextInt(clubStaffTypes.length)];
            ClubStaff clubStaff = new ClubStaff(name, type);
            staffList.add(clubStaff);
        }
    }

    // Function to generate Coaching staff
    private static void generateCoachingStaff() {
    Random random = new Random();
    String[] coachingStaffTypes = {"Head Coach", "Assistant Coach", "Coach"};
    List<String> availableTypes = new ArrayList<>(Arrays.asList(coachingStaffTypes));
    
    // Generate Head Coach
    String headCoachType = "Head Coach";
    String name = generateRandomName();
    int qualificationLevel = 5;
    CoachingStaff headCoach = new CoachingStaff(name, qualificationLevel, headCoachType);
    staffList.add(headCoach);
    coachingStaffList.add(headCoach);
    
    // Remove Head Coach type from availableTypes
    availableTypes.remove(headCoachType);

    // Generate other coaching staff
    for (int i = 0; i < 25; i++) {
        String randomType = availableTypes.get(random.nextInt(availableTypes.size()));
        name = generateRandomName();
        qualificationLevel = random.nextInt(4) + 1; // Qualification level 1 to 4 for other coaching staff
        CoachingStaff coachingStaff = new CoachingStaff(name, qualificationLevel, randomType);
        staffList.add(coachingStaff);
        coachingStaffList.add(coachingStaff);
    }
}

    // Function to generate Training groups
    private static void generateTrainingGroups() {
        Random random = new Random();
        String[] groupIdentifiers = {"A Squad", "B Squad", "Under-13 Squad"};

        for (int i = 0; i < 20; i++) {
            String identifier = groupIdentifiers[random.nextInt(groupIdentifiers.length)] + i;
            int playerCount = random.nextInt(30) + 1;
            String trainingDay = generateRandomTrainingDay();
            TrainingGroup group = new TrainingGroup(identifier, playerCount, trainingDay);
            trainingGroups.add(group);
        }
    }

    // Function to assign coaches to training groups
    private static void assignCoachesToGroups() {
        Random random = new Random();
        List<CoachingStaff> availableCoaches = new ArrayList<>(coachingStaffList);

        for (TrainingGroup group : trainingGroups) {
            if (availableCoaches.isEmpty()) {
                System.out.println("Warning: Not enough coaches available to assign to all groups.");
                break;
            }

            CoachingStaff coach = availableCoaches.remove(random.nextInt(availableCoaches.size()));
            group.setCoach(coach);
        }
    }

    // Function to list all staff
    private static void listAllStaff() {
        System.out.println("\nList of all staff:");
        for (Staff staff : staffList) {
            if (staff instanceof ClubStaff) {
                // Display Club Staff
                ClubStaff clubStaff = (ClubStaff) staff;
                System.out.println("Name: " + staff.getName() + ", Staff Number: " + staff.getStaffNumber() +
                        ", Staff Type: " + clubStaff.getType());
            } else if (staff instanceof CoachingStaff) {
                // Display Coaching Staff
                CoachingStaff coachingStaff = (CoachingStaff) staff;
                System.out.println("Name: " + staff.getName() + ", Staff Number: " + staff.getStaffNumber() +
                        ", Coaching Staff Type: " + coachingStaff.getType() +
                        ", Qualification Level: " + coachingStaff.getQualificationLevel());
            }
        }
    }

    // Function to list staff by category
    private static void listStaffByCategory(Scanner scanner) {
        System.out.println("\nList of staff by category:");
        System.out.println("1. Club Staff");
        System.out.println("2. Coaching Staff");

        try {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listClubStaff();
                    break;
                case 2:
                    listCoachingStaff();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }
    
    // Function to list club staff
    private static void listClubStaff() {
        System.out.println("\nList of club staff:");
        for (Staff staff : staffList) {
            if (staff instanceof ClubStaff) {
                ClubStaff clubStaff = (ClubStaff) staff;
                System.out.println("Name: " + staff.getName() + ", Staff Number: " + staff.getStaffNumber() +
                        ", Staff Type: " + clubStaff.getType());
            }
        }
    }


    //Function to list players coached by a coach
    private static void listPlayersByCoach(Scanner scanner) {
        System.out.print("Enter the coach's name: ");
        String coachName = scanner.next();

        System.out.println("\nPlayers coached by " + coachName + ":");
        for (TrainingGroup group : trainingGroups) {
            CoachingStaff coach = group.getCoach();
            if (coach != null && coach.getName().equalsIgnoreCase(coachName)) {
                List<Player> players = group.getPlayers();
                for (Player player : players) {
                    System.out.println("Player: " + player.getName() + ", Age: " + player.getAge() +
                            ", Player Number: " + player.getPlayerNumber() +
                            ", Group: " + group.getIdentifier());
                }
            }
        }
    }
    //Function to list all players in a club
    private static void listAllPlayers() {
        System.out.println("\nList of all players in the club:");
        for (TrainingGroup group : trainingGroups) {
            List<Player> players = group.getPlayers();
            for (Player player : players) {
                System.out.println("Player: " + player.getName() + ", Age: " + player.getAge() +
                        ", Player Number: " + player.getPlayerNumber() +
                        ", Group: " + group.getIdentifier());
            }
        }
    }
    // Helper function to generate a random name
    private static String generateRandomName() {
        String[] names = {"John", "Jane", "David", "Emma", "Michael", "Sophia", "Christopher", "Olivia", "William", "Ava", "Edgar", "Jeff"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    // Function to list Coaching staff
    private static void listCoachingStaff() {
        System.out.println("\nList of coaching staff:");
        for (Staff staff : staffList) {
            if (staff instanceof CoachingStaff) {
                CoachingStaff coachingStaff = (CoachingStaff) staff;
                System.out.println("Name: " + staff.getName() + ", Staff Number: " + staff.getStaffNumber() +
                        ", Coaching Staff Type: " + coachingStaff.getType() +
                        ", Qualification Level: " + coachingStaff.getQualificationLevel());
            }
        }
    }

    // Function to list all groups
    private static void listAllGroups() {
        System.out.println("\nList of all groups:");
        for (TrainingGroup group : trainingGroups) {
            String coachName = (group.getCoach() != null) ? group.getCoach().getName() : "No Coach Assigned";
            System.out.println("Group: " + group.getIdentifier() + ", Coach: " + coachName + ", Players: " + group.getPlayerCount());
        }
    }

    // Function to list groups by training day
    private static void listGroupsByTrainingDay(Scanner scanner) {
        System.out.print("Enter training day (e.g., Monday, Tuesday, etc.): ");
        String trainingDay = scanner.next();

        System.out.println("\nList of groups training on " + trainingDay + ":");
        for (TrainingGroup group : trainingGroups) {
            String coachName = (group.getCoach() != null) ? group.getCoach().getName() : "No Coach Assigned";

            if (group.getTrainingDay().equalsIgnoreCase(trainingDay)) {
                System.out.println("Group: " + group.getIdentifier() + ", Coach: " + coachName + ", Players: " + group.getPlayerCount());
            }
        }
    }
    // Helper function to generate a random training day
    private static String generateRandomTrainingDay() {
        String[] trainingDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        Random random = new Random();
        return trainingDays[random.nextInt(trainingDays.length)];
    }
}

