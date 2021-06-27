package com.example.demo.controller;

import com.example.demo.dao.CodeDTO;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * @Author Jarrett Luo
 * @Date 2021/6/25 17:19
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "pythonRun")
public class PythonRun {

    @CrossOrigin
    @PostMapping
    public String save(
            // @RequestParam(name = "code") String code
            @RequestBody CodeDTO codeDTO
            ){
        try
        {
            // 创建文件对象
            File fileText = new File("script.py");
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            fileWriter.write(codeDTO.getCode());
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }


        StringBuilder result = new StringBuilder();
        Process proc;
        try {
            // // Python缓冲输出。使用-u
            proc = Runtime.getRuntime().exec("python -u script.py");
            // BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            // System.out.println(in.lines());
            // System.out.println(in.readLine());
            // String line = null;
            // while ((line = in.readLine()) != null) {
            //     System.out.println(line);
            //     // result += line;
            //     result.append(line);
            //     result.append("\n");  // 输出到前端去进行换行
            // }
            // in.close();
            // proc.waitFor();

            // 引入了多线程
            new Thread(() -> {
                BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                try{
                    while((line = br.readLine()) != null) {
                            result.append(line);
                            result.append("\n");  // 输出到前端去进行换行
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }).start();

            BufferedReader br2 = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            while(br2.readLine() != null){
                result.append(br2.readLine());
                result.append("\n");  // 输出到前端去进行换行
            }

            int i = proc.waitFor();
            System.out.println(i);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return result.toString();
    }
}

// 参考代码： https://blog.csdn.net/qq_27721169/article/details/104287254
