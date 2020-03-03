function X = Binomial(n,p, N) 
Bernoulli = rand(n,N)<=p; 
X = sum(Bernoulli);
end

