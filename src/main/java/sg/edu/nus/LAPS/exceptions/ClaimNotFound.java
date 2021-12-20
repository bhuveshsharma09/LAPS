package sg.edu.nus.LAPS.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClaimNotFound extends Exception{


    public ClaimNotFound(String message)
    {
        super(message);
    }
}
