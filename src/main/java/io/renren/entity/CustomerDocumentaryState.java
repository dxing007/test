package io.renren.entity;

public enum CustomerDocumentaryState {
	
	ALREADY("录入","10"), 
	DISPATCHED("已派单", "20"),
	FOLLOW  ("跟进中","30"),
	DROPIN("已上门","40"),
	DEPOSIT("已交定金","50"),
	DONE("已成交","60"),
	UNFINISHED("未完成","35")
	;  
    // 成员变量  
    private String name;  
    private String value;  
    // 构造方法  
    private CustomerDocumentaryState(String name, String value) {  
        this.name = name;  
        this.value = value;  
    }  
    // 普通方法  
    public static String getName(String code) {  
        for (CustomerDocumentaryState c : CustomerDocumentaryState.values()) {  
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
