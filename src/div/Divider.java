package div;

public class Divider {
    private String divident;

    public Divider(String divident) {
        this.divident = divident;
    }

    public String divide(String divisor) {
        String divident_temp = this.divident;
        StringBuilder result = new StringBuilder();
        for (char c : divisor.toCharArray()) {
            int char_index = divident_temp.indexOf(c);

            if (char_index != -1) {
                result.append(divident_temp.substring(0, char_index));
                divident_temp = divident_temp.substring(char_index + 1);
            } else {
                return "";
            }
        }
        return result.toString() + divident_temp;
    }
}
