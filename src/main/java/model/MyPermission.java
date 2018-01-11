package model;

import org.springframework.security.acls.model.Permission;

public class MyPermission implements Permission{

    @Override
    public int getMask() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getPattern() {
        // TODO Auto-generated method stub
        return null;
    }

}
