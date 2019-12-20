package vendaingressos.model;

public class Person {
	
	public int id;
	public String name;
	
	public Person(){}
	
	public Person(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
