    public static void main(String... args) throws Exception {
        // Take six command-line parameters
        if (args.length < 6) {
            print("You must give me six Pokemon to analyze");
            System.exit(-1);
        }

        // This bit of JDBC magic I provide as a free gift :-)
        // The rest is up to you.
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:pokemon.db")) {
            for (String arg : args) {
                print("Analyzing " + arg);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Pokemon WHERE pokedex_number = 'arg’");
                Set<String> strong = new HashSet<String>();
                Set<String> weak = new HashSet<String>();
                ResultSetMetaData rsmd = result.getMetaData();

                while (result.next()) {
                    for (int i = 2 ; i <= 19; i++) {
                        String name = rsmd.getColumnName(i);
                        if (result.getInt(i) > 1) {
                            strong.add(name);

                        } else if (result.getInt(i) < 1) {
                            weak.add(name);

                        }
                        String poke = rsmd.getColumnname(30);
                        String type1 = rsmd.getColumnname(36);
                        String type2 = rsmd.getColumnname(37);

                        System.out.println(poke + " ( " + type1 + " " + type2 + " ) " + " is strong against " + strong + " but weak against " + weak);
                    } con.close();
                    strong.clear();
                    weak.clear();


                    // Analyze the pokemon whose pokedex_number is in "arg"

                    // You will need to write the SQL, extract the results, and compare
                    // Remember to look at those "against_NNN" column values; greater than 1
                    // means the Pokemon is strong against that type, and less than 1 means
                    // the Pokemon is weak against that type
                }

                String answer = input("Would you like to save this team? (Y)es or (N)o: ");
                if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("YES")) {
                    String teamName = input("Enter the team name: ");

                    // Write the pokemon team to the "teams" table
                    print("Saving " + teamName + " ...");
                }
                else {
                    print("Bye for now!");
                }
            }
        }
