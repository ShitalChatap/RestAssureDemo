package com.maveric.project.pojo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EmployeePojo 
{
	
	        private String name;
	        int salary , age;
			public EmployeePojo()
			{
				
			}
			public EmployeePojo(String name, int salary, int age)
			{
				super ();
				this.name=name;
				this.salary=salary;
				this.age=age;
				
			}
			public String getName()
			{
				return name;
			}
			public void setName()
			{
				this.name=name;
			}
			 public int getSalary()
			 {
				 return salary;
			 }
			  public void setSalary()
			  {
				  this.salary=salary;
			  }
			  
			  public int getAge()
			  {
				  return age;
			  }
			  
			  public void setAge()
			  {
				  this.age=age;
			  }
			  
			  @Override
				public String toString() 
				{
					return "EmployeePojo [name="+name+",salary="+salary+",age="+age+"]";
				}
			  
			 
}
