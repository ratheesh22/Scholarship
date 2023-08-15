package com.main;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class StudentController {
     
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository st;
	
	//User Login Page
	@RequestMapping("/login")
	public String Login()
	{
		return "Login";
	}
	
	//User can see their status
	@PostMapping("/status")
	public String Status(Model model, @RequestParam("id") String userid, @RequestParam("pwd") String pwd)
	{
		List<Student> s=studentService.getoneStudents(userid);
		for(Student ss:s)
		{
			if(ss.getPassword().matches(pwd))
			{
				
				model.addAttribute("student", studentService.getoneStudents(userid));
				return "status";
			}
		}
		return "loginfailed";
	}
	
	//User Register Page
	@GetMapping("/register")
	public String Register(Model model)
	{
		Student student=new Student();
		model.addAttribute("student", student);
		return "register";
	}
	//Save User Details
	@PostMapping("/savestudent")
	public String SaveStudent(@ModelAttribute("student") Student student)
	{
		studentService.saveStudent(student);
		return "detailssaved";
	}
	//login to admin page
	@GetMapping("/a")
	public String Admin()
	{
		return "admin";
	}
	//admin can see user details
	@GetMapping("/userdetails1")
	public String UserDetail(Model model)
	{
		model.addAttribute("student", studentService.getAllStudents());
		return "userdetails";
	}
	//admin can approve or reject
	@GetMapping("/action/{id}")
	public String ApproveOrReject(@PathVariable ( value = "id") long id, Model model)
	{
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "action";
	}
	//admin approved scolarship
	@GetMapping("/accept/{id}")
	public String Accept(@PathVariable ( value = "id") long id)
	{
		List<Student> s=studentService.getoneStudents1(id);
		for(Student s1:s)
		{
			s1.setAction("Approved");
			st.save(s1);
		}
		return "accepted";
	}
	//admin rejected scholarship
	@GetMapping("/reject/{id}")
	public String Reject(@PathVariable ( value = "id") long id)
	{
		List<Student> s=studentService.getoneStudents1(id);
		for(Student s1:s)
		{
			s1.setAction("Rejected");
			st.save(s1);
		}
		return "rejected";
	}	
}
