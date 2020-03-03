function [str] = RandKey_v2(pmfPT)
alpha = ['A':'Z','a':'z'];
len = -1;
while len <0
    len = floor(normrnd(10,5));
end
str = "";
for k=1:len
    r = rand();
    i = 1 + sum(r>cumsum(pmfPT));
    str = str + alpha(i);
end
str = char(str);