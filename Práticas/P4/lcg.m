function y = lcg (X0,a,c,m,N)

y = zeros(1,N);
y(1)=X0;
for i=2:N
    y(i) = rem(a*y(i-1)+c,m);
end

