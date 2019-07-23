package io.renren.entity;

public enum CustomerUserTypeEnum {
	
	ADMIN("系统管理员","admin"), 
	HOSPITAL_ADMIN("医院管理员", "hospital_admin")
	;  
    // 成员变量  
    private String name;  
    private String value;  
    // 构造方法  
    private CustomerUserTypeEnum(String name, String value) {  
        this.name = name;  
        this.value = value;  
    }  
    // 普通方法  
    public static String getName(String code) {  
        for (CustomerUserTypeEnum c : CustomerUserTypeEnum.values()) {  
            if (c.getValue().equals(code)) {  
                return c.name;  
            }  
        }  
        return null;  
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}  
   
    
    
}
