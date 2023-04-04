package com.Jason.ucenter.service.impl;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.MD5;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.Jason.ucenter.entity.UcenterMember;
import com.Jason.ucenter.entity.vo.LoginVO;
import com.Jason.ucenter.entity.vo.RegisterVO;
import com.Jason.ucenter.mapper.UcenterMemberMapper;
import com.Jason.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

        /**
         * 会员登录
         * @param loginVo
         * @return
         */

    @Override
    public String login(LoginVO loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) ||
        StringUtils.isEmpty(password)) {
            throw new CiliException(20001,"手机号和密码不能为空");
        }
        //获取会员
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(null == member) {
            throw new CiliException(20001,"手机号不存在!");
        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new CiliException(20001,"密码错误");
        }
        //校验是否被禁用
        if(member.getIsDisabled()) {
            throw new CiliException(20001,"用户被禁用");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;

    }

        /**
         * 会员注册
         * @param registerVo
         */

    @Override
    public void register(RegisterVO registerVo) {

        //获取注册信息，进行校验

        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数

        if(StringUtils.isEmpty(mobile) ||

        StringUtils.isEmpty(nickname) ||

        StringUtils.isEmpty(password) ||

        StringUtils.isEmpty(code)) {

            throw new CiliException(20001,"参数不能为空");

        }


        //校验校验验证码

        //从redis获取发送的验证码

        String mobleCode = redisTemplate.opsForValue().get(mobile);

        if(!code.equals(mobleCode)) {

            throw new CiliException(20001,"验证码错误");

        }

        //查询数据库中是否存在相同的手机号码

        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));

        if(count.intValue() > 0) {
            throw new CiliException(20001,"该手机号已注册");

        }
        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }
}

