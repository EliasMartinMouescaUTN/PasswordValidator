import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

// https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-63b.pdf

public class PasswordValidator {
    private File passwordBlacklist;
    private final int minimumLength = 8;

    // Safe to pass null
    PasswordValidator(String pathToPasswordBlacklist){

        if (pathToPasswordBlacklist != null) {
            passwordBlacklist = new File(pathToPasswordBlacklist);
            if (!passwordBlacklist.isFile()) {
                System.out.println("Given path is not a valid file! blacklist test will not be conducted.");
                passwordBlacklist = null;
            }
        } else {
            System.out.println("Blacklist path is null, blacklist test will not be conducted!");
            passwordBlacklist = null;
        }

    }

    boolean isLongEnough(String potentialPassword){
        return potentialPassword.length() >= minimumLength;
    }

    boolean matchesBlacklist(String potentialPassword){

        if (passwordBlacklist == null) return false;

        try {
            Scanner scanner = new Scanner(passwordBlacklist);

            while (scanner.hasNextLine())
                if (Objects.equals(scanner.nextLine(), potentialPassword)) return true;

        } catch (FileNotFoundException e) {
            // It should never reach this block, we validated the blacklist path in the constructor... but who knows.
            return false;
        }

        return false;

    }

    boolean validate(String potentialPassword){
        return isLongEnough(potentialPassword) && !(matchesBlacklist(potentialPassword));
    }

}
