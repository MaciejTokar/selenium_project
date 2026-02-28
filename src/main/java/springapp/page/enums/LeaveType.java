package springapp.page.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum LeaveType {
    CAN_BEREAVEMENT("CAN - Bereavement"),
    CAN_FMLA("CAN - FMLA"),
    CAN_MATTERNITY("CAN - Matternity"),
    CAN_PERSONAL("CAN - Personal"),
    CAN_VACATION("CAN - Vacation"),
    US_BEREAVEMENT("US - Bereavement"),
    US_FMLA("US - FMLA"),
    US_MATTERNITY("US - Matternity"),
    US_PERSONAL("US - Personal"),
    US_VACATION("US - Vacation");

    private final String name;

    public static List<String> listOfRequiredLeaveTypes() {
        return Arrays.stream(LeaveType.values())
                .map(LeaveType::getName)
                .collect(Collectors.toList());
    }
}
