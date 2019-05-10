package Core.db.Service;

import Core.Config.DataBaseConfig;
import Core.data.code;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataBaseConfig.class})
public class codeServiceTest {
    @Autowired
    codeService codeService;
    @Test
    public void savecode() {
        String input="给定 nums = [2, 7, 11, 15], target = 9";
        String output="因为 nums[0] + nums[1] = 2 + 7 = 9\n" +
                "所以返回 [0, 1]";
        String detial="给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。\n" +
                "\n" +
                "你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。";
        String content=" public int[] twoSum(int[] nums, int target) {\n" +
                "        int i=0;\n" +
                "        int j=0;\n" +
                "        boolean flag=true;\n" +
                "        for(i=0;i<nums.length;i++){\n" +
                "            for(j=i+1;j<nums.length;j++){\n" +
                "                if((nums[i]+nums[j])==target){\n" +
                "                    flag=false;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "            if(flag==false){\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        int[] t=new int[2];\n" +
                "        t[0]=i;\n" +
                "        t[1]=j;\n" +
                "        return t;\n" +
                "    }";
        String otherdetial="";
        codeService.savecode(new code(1,new Date(),input,output,detial,content,otherdetial));
    }
}