//package paasta.delivery.pipeline.common.api.user;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
///**
// * Created by hrjin on 2017-06-09.
// */
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Before
//    public void setUp() throws Exception{
//    }
//
//    @After
//    public void tearDown() throws Exception{
//    }
//
//
//    String userTid = "test-user-" + UUID.randomUUID().toString();
//
//    /**
//     * Gets User list valid return list.
//     *
//     * @throws Exception
//     */
//    @Test
//    public void getUserList_Valid_ReturnList(Pageable pageable) throws Exception{
//        String name = "test";
//        String tellPhone = "010-2234-4521";
//        String cellPhone = "010-2233-4091";
//        String email = "hyunlee@bluedigm.com";
//        String company = "BD";
//
//
//        List<User> testList = new ArrayList<>();
//        User testModel = new User();
//        testModel.setId(userTid);
//        testModel.setName(name);
//        testModel.setTellPhone(tellPhone);
//        testModel.setCellPhone(cellPhone);
//        testModel.setEmail(email);
//        testModel.setCompany(company);
//
//        testList.add(testModel);
//
//        when(userRepository.findAll()).thenReturn(testList);
//
//        UserList resultList = userService.getUserList("", pageable);
//
//        assertThat(resultList).isNotNull();
//        assertEquals(userTid, resultList.getUsers().get(0).getId());
//    }
//
//
//    @Test
//    public void getUser_Valid_ReturnModel() throws Exception{
//        User testModel = new User();
//        testModel.setId(userTid);
//
//        when(userRepository.findOne(testModel.getId())).thenReturn(testModel);
//
//        User resultModel = userService.getUser(testModel.getId());
//
//        assertThat(resultModel).isNotNull();
//        assertEquals(userTid, resultModel.getId());
//    }
//
//
//    @Test
//    public void createUser_Valid_ReturnModel() throws Exception{
//        String name = "코난";
//        String email = "conan@naver.com";
//        String company = "BD";
//
//        User testModel = new User();
//
//        testModel.setId(userTid);
//        testModel.setName(name);
//        testModel.setEmail(email);
//        testModel.setCompany(company);
//
//
//        when(userRepository.save(testModel)).thenReturn(testModel);
//
//        User resultModel = userService.createUser(testModel);
//
//        assertThat(resultModel).isNotNull();
//        assertEquals(userTid, resultModel.getId());
//        assertEquals(name, resultModel.getName());
//
//        userService.deleteUser(userTid);
//        System.out.print("delete ::: " + userTid);
//    }
//
//    @Test
//    public void updateUser_Valid_ReturnModel() throws Exception{
//        String name = "코난";
//        String email = "conan@naver.com";
//        String company = "BD";
//
//        User testModel = new User();
//
//        testModel.setId(userTid);
//        testModel.setName(name);
//        testModel.setEmail(email);
//        testModel.setCompany(company);
//
//        when(userRepository.save(testModel)).thenReturn(testModel);
//        System.out.println("test model :::" + testModel);
//
//
//
//        String nameModify = "water";
//        String emailModify = "water@bluedigm.com";
//        String companyModify = "BD";
//
//        User resultModel = new User();
//
//        resultModel.setId(testModel.getId());
//        resultModel.setName(nameModify);
//        resultModel.setEmail(emailModify);
//        resultModel.setCompany(companyModify);
//
//        System.out.println("modify model :::" + resultModel);
//
//
//        userService.updateUser(testModel.getId(), resultModel);
//        System.out.println("final model :::" + resultModel);
//
//        assertThat(testModel).isNotNull();
//        assertEquals(userTid, testModel.getId());
//
//        userService.deleteUser(userTid);
//        System.out.print("delete ::: " + userTid);
//    }
//
//    @Test
//    public void deleteUser_Valid_ReturnString() throws Exception{
//        String name = "코난";
//        String email = "conan@naver.com";
//        String company = "BD";
//
//        User testModel = new User();
//
//        testModel.setId(userTid);
//        testModel.setName(name);
//        testModel.setEmail(email);
//        testModel.setCompany(company);
//
//        doNothing().when(userRepository).delete(testModel.getId());
//
//        String resultString = userService.deleteUser(testModel.getId());
//
//        assertThat(resultString).isNotNull();
//        assertEquals("delete completed", resultString);
//    }
//}
