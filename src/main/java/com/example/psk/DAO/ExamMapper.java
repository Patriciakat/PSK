//package com.example.psk.DAO;
//
//import com.example.psk.entities.Exam;
//import org.apache.ibatis.annotations.*;
//import java.util.List;
//
//@Mapper
//public interface ExamMapper {
//    @Select("SELECT * FROM EXAM")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "title", column = "title"),
//            @Result(property = "score", column = "score"),
//            @Result(property = "students", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.ExamMapper.findStudentsByExamId"))
//    })
//    List<Exam> findAll();
//
//    @Select("SELECT * FROM EXAM WHERE id = #{id}")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "title", column = "title"),
//            @Result(property = "score", column = "score"),
//            @Result(property = "students", column = "id",
//                    many = @Many(select = "com.example.psk.DAO.ExamMapper.findStudentsByExamId"))
//    })
//    Exam findById(Long id);
//
//    @Select("SELECT * FROM EXAM WHERE title LIKE #{title}")
//    List<Exam> findByTitle(String title);
//
//    @Insert("INSERT INTO EXAM (title, score) VALUES (#{title}, #{score})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Exam exam);
//
//    @Update("UPDATE EXAM SET title=#{title}, score=#{score} WHERE id=#{id}")
//    void update(Exam exam);
//
//    @Delete("DELETE FROM EXAM WHERE id=#{id}")
//    void delete(Long id);
//
//    // For many-to-many relationship queries
//    @Select("SELECT e.* FROM EXAM e JOIN STUDENT_EXAM se ON e.id = se.exam_id WHERE se.student_id = #{studentId}")
//    List<Exam> findExamsByStudentId(Long studentId);
//
//    @Select("SELECT s.* FROM STUDENT s JOIN STUDENT_EXAM se ON s.id = se.student_id WHERE se.exam_id = #{examId}")
//    List<com.example.psk.entities.Student> findStudentsByExamId(Long examId);
//}
