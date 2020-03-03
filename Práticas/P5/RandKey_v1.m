function [str] = RandKey_v1()
alpha = ['A':'Z','a':'z'];
len = randi([3 20]);
str = alpha(randi(numel(alpha),1,len));

