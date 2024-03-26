package com.artdevs.restcontroller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Log;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.ErrorResponseDTO;
import com.artdevs.dto.ReponseDTO;
import com.artdevs.dto.post.ReportDTO;
import com.artdevs.dto.user.UserUpdateDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.mapper.post.ReportMapper;
import com.artdevs.repositories.user.LogRepository;
import com.artdevs.services.ReportService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_admin)
public class AdminRestController {
	@Autowired LogRepository logRepository;
	@Autowired ReportService reportservice;
	@Autowired UserService userservice;
	
	
	@GetMapping("/statistics-uptime-of-user")
	public ResponseEntity<?> getUptimeOfUser(){
		Calendar calendar = Calendar.getInstance(); // Lấy thời gian hiện tại
        calendar.add(Calendar.HOUR_OF_DAY, -24); // Giảm đi 24 giờ
        Date startTime = calendar.getTime(); // Lấy thời điểm sau khi giảm 24 giờ

        Date endTime = new Date(); // Thời điểm hiện tại

        List<Log> result = logRepository.findByTimeRange(startTime, endTime);
        System.out.println("check RS:"+ result);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/statistics-user")
	public ResponseEntity<?> getCountUser(){
		long countuser = userservice.coutUser();
		ReponseDTO<Long> result = new ReponseDTO<>();
		result.setMessage("Thống Kê Số lượng user:");
		result.setStatusCode(200);
		result.setModel(countuser);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/statistics-user-report")
	public ResponseEntity<?> getuserreport(){
		List<ReportDTO> rp = reportservice.findAll().stream().map(t -> ReportMapper.convertToReprotDTO(t)).collect(Collectors.toList());
		ReponseDTO<List<ReportDTO>> result = new ReponseDTO<>();
		result.setMessage("Thống Kê Report:");
		result.setStatusCode(200);
		result.setModel(rp);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/statistics-mentor")
	public ResponseEntity<?> getCountmentor(){
		long countmentor = userservice.coutMentor();
		ReponseDTO<Long> result = new ReponseDTO<>();
		result.setMessage("Thống Kê Số lượng mentor:");
		result.setStatusCode(200);
		result.setModel(countmentor);
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/statistics-report")
	public ResponseEntity<?> getreport(){
		List<ReportDTO> rp = reportservice.findAll()
			    .stream()
			    .map(t -> ReportMapper.convertToReprotDTO(t))
			    .sorted(Comparator.comparing(ReportDTO::getTimeReport, Comparator.nullsLast(Comparator.naturalOrder())).reversed()) // Sắp xếp theo trường thời gian (assumed)
			    .collect(Collectors.toList());
		ReponseDTO<List<ReportDTO>> result = new ReponseDTO<>();
		result.setMessage("Thống Kê Report:");
		result.setStatusCode(200);
		result.setModel(rp);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getlist-user-fristreport")
	public ResponseEntity<?> fristreport(){
		ReponseDTO<List<User>> result = new ReponseDTO<>();
		
		List<User> rp = userservice.getUserReport1();
		if(rp != null) {
			result.setMessage("Thống Kê User bị report 1 lần:");
			result.setStatusCode(200);
			result.setModel(rp);
		}else {
			result.setMessage("Error");
			result.setStatusCode(401);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getlist-user-secondreport")
	public ResponseEntity<?> secondreport(){
		ReponseDTO<List<User>> result = new ReponseDTO<>();
		
		List<User> rp = userservice.getUserReport2();
		if(rp != null) {
			result.setMessage("Thống Kê User bị report 2 lần:");
			result.setStatusCode(200);
			result.setModel(rp);
		}else {
			result.setMessage("Error");
			result.setStatusCode(401);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/new-user-of-rangetime")
	public ResponseEntity<?> viewNewUser(@RequestParam("starttime") String starttime){
	      // Xử lý dữ liệu ở đây
		ReponseDTO<List<User>> result = new ReponseDTO<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		  try {
	            Date starttimeDate = dateFormat.parse(starttime);
	   
	            List<User> data = userservice.getNewUser(starttimeDate);
	    		System.out.println("check data" + data);
	    		result.setMessage("Thống Kê user từ ngày:" + starttimeDate);
	    		result.setStatusCode(200);
	    		result.setModel(data);
	        } catch (ParseException e) {
	            System.out.println("Error parsing start time: " + e.getMessage());
	        }
		
		return ResponseEntity.ok(result);
	}
	@PostMapping("/new-mentor-of-rangetime")
	public ResponseEntity<?> viewNewMentor(@RequestParam("starttime") String starttime){
	      // Xử lý dữ liệu ở đây
		ReponseDTO<List<User>> result = new ReponseDTO<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		  try {
	            Date starttimeDate = dateFormat.parse(starttime);
	   
	            List<User> data = userservice.getNewMentor(starttimeDate);
	    		System.out.println("check data" + data);
	    		result.setMessage("Thống Kê Mentor từ ngày:" + starttimeDate.getDate());
	    		result.setStatusCode(200);
	    		result.setModel(data);
	        } catch (ParseException e) {
	            System.out.println("Error parsing start time: " + e.getMessage());
	        }
		
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/find-user")
	public ResponseEntity<?> findUser(@RequestParam("userID") String userID){
		 ReponseDTO<User> result = new ReponseDTO<>();
		 User data = userservice.findUserById(userID);
		
 		if(data != null) {
 			System.out.println("check data" + data);
 	 		result.setMessage("Thống Kê user mới từ ngày:" );
 	 		result.setStatusCode(200);
 	 		result.setModel(data);
 		}else {
 			result.setMessage("Không tìm thấy User " );
 	 		result.setStatusCode(401);
 		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("get-account-band")
	public ResponseEntity<?> getaccountband(){
		List<UserUpdateDTO> data = userservice.getUserBand().stream().map(t -> UserMapper.UserconvertToUpdateDTO(t)).collect(Collectors.toList());;
		 ReponseDTO<List<UserUpdateDTO>> result = new ReponseDTO<>();
			result.setMessage("Thống Kê Account bị band:" );
	 		result.setStatusCode(200);
	 		result.setModel(data);
			return ResponseEntity.ok(result);
	}

	@PostMapping("/update-user")
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO UserUpdate){
		 ReponseDTO<UserUpdateDTO> result = new ReponseDTO<>();
		User u = userservice.findByEmail(UserUpdate.getEmail());
		
		if(u !=null) {
			u.setAccountNonLocked(UserUpdate.isAccountNonLocked());
			u.setUserId(UserUpdate.getUserId());
			u.setEmail(UserUpdate.getEmail());
			u.setCreateDate(UserUpdate.getCreateDate());
			u.setRole(UserUpdate.getRole());
			userservice.saveUser(u);
			result.setStatusCode(200);
			result.setMessage("Update User Thành Công !");
			result.setModel(UserUpdate);
		}else {
			result.setStatusCode(401);
			result.setMessage("Update User Ko Thành Công !");
		}
		return ResponseEntity.ok(result);
	}

	
	
}
