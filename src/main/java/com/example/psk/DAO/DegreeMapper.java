//package com.example.psk.DAO;
//
//import com.example.psk.entities.Degree;
//import org.apache.ibatis.annotations.*;
//import java.util.List;
//
//@Mapper
//public interface DegreeMapper {
//    @Select("SELECT * FROM DEGREE")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "title", column = "title"),
//            @Result(property = "students", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.StudentMapper.findByDegreeId"))
//    })
//    List<Degree> findAll();
//
//    @Select("SELECT * FROM DEGREE WHERE id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "title", column = "title"),
//            @Result(property = "students", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.StudentMapper.findByDegreeId"))
//    })
//    Degree findById(Long id);
//
//    @Select("SELECT * FROM DEGREE WHERE title = #{title}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "title", column = "title"),
//            @Result(property = "students", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.StudentMapper.findByDegreeId"))
//    })
//    Degree findByTitle(String title);
//
//    @Insert("INSERT INTO DEGREE (title) VALUES (#{title})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Degree degree);
//
//    @Update("UPDATE DEGREE SET title=#{title} WHERE id=#{id}")
//    void update(Degree degree);
//
//    @Delete("DELETE FROM DEGREE WHERE id=#{id}")
//    void delete(Long id);
//
//    @Select("SELECT COUNT(*) FROM STUDENT WHERE degree_id = #{degreeId}")
//    Long countStudents(Long degreeId);
//}
