package SpringJPA.demo1.Controller;


import SpringJPA.demo1.DTO.AuthRequest;
import SpringJPA.demo1.DTO.AuthResponse;
import SpringJPA.demo1.Empty.Product;
import SpringJPA.demo1.Empty.UserInfo;
import SpringJPA.demo1.Repository.UserInfoRepository;
import SpringJPA.demo1.Service.JwtService;
import SpringJPA.demo1.Service.ProductService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService service;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("list-user")
    public ResponseEntity<List<UserInfo>>listUser(){
        List<UserInfo>userInfoList=userInfoRepository.findAll();
        return ResponseEntity.ok(userInfoList);
    }
    @PutMapping("update-role/{id}")
    public ResponseEntity<UserInfo>updateUser(@PathVariable Long id,@Valid @RequestBody UserInfo a){
        UserInfo userInfo=service.updateRoles(id,a);
        return ResponseEntity.ok(userInfo);
    }


    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void>delete(@PathVariable("id") Long id){
        userInfoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
    //Đăng nhập
    @PostMapping("authenticate")
    public ResponseEntity<?> authenticateAndGetoken(@RequestBody AuthRequest authRequest){

        try {
            if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Username and password are required"));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role.toUpperCase();
            }
            System.out.println("Login role: " + role); // Debug
            String jwt = jwtService.generateToken(authRequest.getUsername());

            // Thêm redirectTo dựa trên vai trò
            return ResponseEntity.ok(new AuthResponse(true, jwt, role, "Login successful"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse(false, "Invalid username or password"));
        }
    }
    //Đăng kí
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody UserInfo userInfo) {
        try {
            // Kiểm tra email đã tồn tại chưa
            if (userInfoRepository.findByEmail(userInfo.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Email đã tồn tại trong hệ thống"));
            }

            // Kiểm tra tên đã tồn tại chưa
            if (userInfoRepository.findByName(userInfo.getName()).isPresent()) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Tên người dùng đã tồn tại"));
            }

            if (userInfo.getPassword() == null || userInfo.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(new AuthResponse(false, "Mật khẩu không được để trống"));
            }

            // Đặt vai trò mặc định là ROLE_USER
            userInfo.setRoles("user");
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);

            return ResponseEntity.ok(new AuthResponse(true, null, "ROLE_USER", "Đăng ký thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new AuthResponse(false, "Đăng ký thất bại: " + e.getMessage()));
        }
    }
}

