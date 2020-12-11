package cc.sybx.leetcode.app;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个蒸熟数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标
 * 你可以假设每种输入只会对应一个答案。但是数组中同一个元素不能使用两遍
 *
 * 示例：
 *      给定 nums = [2, 7, 11, 15], target = 9
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 *
 *      简单难度
 * @author : yuanzp
 * @Date : 2020/11/5 9:25 下午
 */
public class TwoSum {
    private final Logger logger = LoggerFactory.getLogger(TwoSum.class);

    /**
     * 最容易想到的方法就是枚举数组中的每一个数 x,寻找数组中是否存在 target - x
     * 当我们使用遍历整个数组的方式寻找 target - x时,需要注意到每一个位于 x之前的元素和 x 匹配过,
     * 因此不需要在进行匹配.而每一个元素不能被使用两次,所以我们只需要在 x后面的元素中寻找 target - x
     *
     * 时间复杂度:O(N^2),其中N是数组中的元素数量.最坏情况下数组中任意两个数都要被匹配一次.
     * 空间复杂度:O(1).
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target){
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; ++j){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 使用哈希表,可以将寻找 target - x的时间复杂度降低到从O(N)降低到O(1).
     * 这样我们创建一个哈希表，对于每一个 x,我们首先查询哈希表中是否存在 target - x,然后将 x插入到哈希表中,即可保证不会让 x和自己匹配.
     *
     * 时间复杂度:O(N),其中N是数组中的元素数量.对于每一个元素 x，我们可以O(1)地寻找 target - x.
     * 空间复杂度:O(N),其中N是数组中的元素数量.主要为哈希表开销
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target){
        Map<Integer, Integer> hashatble = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; ++i){
            if(hashatble.containsKey(target - nums[i])){
                return new int[]{hashatble.get(target - nums[i]), i};
            }
            hashatble.put(nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void test(){
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        logger.info(Arrays.toString(twoSum(nums, target)));
        logger.info(Arrays.toString(twoSum1(nums, target)));
    }
}
