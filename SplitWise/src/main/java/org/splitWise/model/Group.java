package org.splitWise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Group {

    private final String groupId;
    private final String groupDescription;
    private final String groupName;
    private final List<String> users;
}
